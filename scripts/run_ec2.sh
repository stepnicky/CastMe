#!/bin/bash

REGION="eu-central-1"
MOUNT_POINT="/mnt/efs"

if [ -z "$AWS_PROFILE" ]; then
  echo "The AWS_PROFILE environment variable is not set."
  echo "Set it before running the script, e.g.:"
  echo "  export AWS_PROFILE=my-profile"
  exit 1
fi

read -rp "Enter the EC2 alias (from ~/.ssh/config): " REMOTE_HOST

if [[ -z "$REMOTE_HOST" ]]; then
  echo "EC2 alias cannot be empty."
  exit 1
fi

echo "Running the EC2 setup script (ec2_setup.sh) on $REMOTE_HOST..."
ssh "$REMOTE_HOST" /bin/bash < ec2_setup.sh

echo "Fetching FileSystemId from AWS..."

FS_ID=$(aws efs describe-file-systems \
  --query "FileSystems[*].FileSystemId" \
  --output text | head -n1)

if [[ -z "$FS_ID" ]]; then
  echo "No FileSystemId found for profile $AWS_PROFILE"
  exit 1
fi

echo "Found FileSystemId: $FS_ID"

FSTAB_LINE="${FS_ID}.efs.${REGION}.amazonaws.com:/ $MOUNT_POINT nfs4 nfsvers=4.1,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 0 0"

echo "Connecting to EC2 ($REMOTE_HOST) and mounting EFS"

ssh "$REMOTE_HOST" /bin/bash <<EOF

  set -e

  echo "Installing nfs-common (if not already installed)..."
  sudo apt-get update -qq
  sudo apt-get install -y nfs-common

  echo "Creating mount directory: $MOUNT_POINT"
  sudo mkdir -p "$MOUNT_POINT"

  echo "Adding entry to /etc/fstab..."
  FSTAB_EXISTS=\$(grep -F "${FS_ID}" /etc/fstab || true)
  if [[ -z "\$FSTAB_EXISTS" ]]; then
    echo "$FSTAB_LINE" | sudo tee -a /etc/fstab > /dev/null
    echo "Entry has been added to /etc/fstab"
  else
    echo "Entry already exists — skipping."
  fi

  echo "Mounting EFS..."
  sudo mount "$MOUNT_POINT"

  echo "Verifying that EFS is mounted:"
  findmnt "$MOUNT_POINT" || echo "EFS was not mounted correctly."

EOF

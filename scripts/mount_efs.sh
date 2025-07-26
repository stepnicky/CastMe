#!/bin/bash

REGION="eu-central-1"
MOUNT_POINT="/mnt/efs"

if [ -z "$AWS_PROFILE" ]; then
  echo "Zmienna środowiskowa AWS_PROFILE nie jest ustawiona."
  echo "Ustaw ją przed uruchomieniem skryptu, np.:"
  echo "  export AWS_PROFILE=my-profile"
  exit 1
fi

read -rp "Podaj alias ec2 (z ~/.ssh/config): " REMOTE_HOST

if [[ -z "$REMOTE_HOST" ]]; then
  echo "Alias ec2 nie może być pusty."
  exit 1
fi

echo "Pobieranie FileSystemId z AWS..."

FS_ID=$(aws efs describe-file-systems \
  --query "FileSystems[*].FileSystemId" \
  --output text | head -n1)

if [[ -z "$FS_ID" ]]; then
  echo "Nie znaleziono żadnego FileSystemId dla profilu $AWS_PROFILE"
  exit 1
fi

echo "Znaleziono FileSystemId: $FS_ID"

FSTAB_LINE="${FS_ID}.efs.${REGION}.amazonaws.com:/ $MOUNT_POINT nfs4 nfsvers=4.1,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 0 0"

echo "Łączenie z EC2 ($REMOTE_HOST) i uruchamianie konfiguracji..."

ssh "$REMOTE_HOST" /bin/bash <<EOF

  set -ec2

  echo "Instalacja nfs-common (jeśli nie ma)..."
  sudo apt-get update -qq
  sudo apt-get install -y nfs-common

  echo "Tworzenie katalogu montowania: $MOUNT_POINT"
  sudo mkdir -p "$MOUNT_POINT"

  echo "Dodanie wpisu do /etc/fstab..."
  FSTAB_EXISTS=\$(grep -F "${FS_ID}" /etc/fstab || true)
  if [[ -z "\$FSTAB_EXISTS" ]]; then
    echo "$FSTAB_LINE" | sudo tee -a /etc/fstab > /dev/null
    echo "Wpis został dodany do /etc/fstab"
  else
    echo "Wpis już istnieje — pomijam dodanie."
  fi

  echo "Montowanie EFS..."
  sudo mount "$MOUNT_POINT"

  echo "Sprawdzenie poprawności montowania:"
  findmnt "$MOUNT_POINT" || echo "EFS nie został zamontowany poprawnie."

EOF

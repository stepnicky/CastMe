const deleteAttachment = document.querySelectorAll(".attachment-delete");

deleteAttachment.forEach(del => {
   del.addEventListener("click", e => {
      const button = e.target.parentElement;
      const attachmentId = button.dataset.id;
      fetch(`/attachment/${attachmentId}/delete`, {
         method: "DELETE"
      })
      .then(res => res.json())
      .then(res => console.log(res))
      .catch(err => console.log(err));
      const parent = button.parentElement;
      parent.remove();
   });
});
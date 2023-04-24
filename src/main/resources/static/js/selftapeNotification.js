const selftapeLinks = document.querySelectorAll(".selftape-link");

selftapeLinks.forEach(link => {
   link.addEventListener("click", function (e) {
       e.preventDefault();
       const actorId = link.dataset.actorId;
       const roleId = link.dataset.roleId;
       fetch("/director/selftape/viewed", {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({actorId, roleId})
       }).then(res => res.json())
           .then(data => {
               console.log(data);
               window.location.href = link.href;
           })
           .catch(err => console.error(err));
   })
});
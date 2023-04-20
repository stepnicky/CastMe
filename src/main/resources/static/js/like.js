const hearts = document.querySelectorAll(".heart");

hearts.forEach(heart => {
   heart.addEventListener("click", e => {
      const actorRoleId = e.target.dataset.id;
      if(e.target.classList.contains("far")) {
         fetch("/actor-role/status/change", {
            method: 'POST',
            headers: {
               'Content-Type': 'application/json'
            },
            body: JSON.stringify({actorRoleId: actorRoleId, action: "accepted"})
         }).then(res => res.json())
         .then(res => console.log(res))
         .catch(err => console.log(err));
         e.target.classList.remove("far");
         e.target.classList.add("fas");
         const message = "You like it!";
         e.target.nextElementSibling.innerHTML = message;
      } else {
         fetch("/actor-role/status/change", {
            method: 'POST',
            headers: {
               'Content-Type': 'application/json'
            },
            body: JSON.stringify({actorRoleId: actorRoleId, action: "viewed"})
         }).then(res => res.json())
             .then(res => console.log(res))
             .catch(err => console.log(err));
         e.target.classList.remove("fas");
         e.target.classList.add("far");
         const message = "Hit heart and take part!";
         e.target.nextElementSibling.innerHTML = message;
      }
   });
});
const bell = document.querySelector(".bell");
const notificationMenu = document.querySelector(".notification-menu");
const notificationNumber = document.querySelector(".notif-num");
const notifications = document.querySelectorAll(".notification-menu li .message");

if(notifications.length > 0) {
    bell.addEventListener("click", () => {
        const actorRolesIds = [];
        [...notifications].forEach(n =>
            actorRolesIds.push(
                Number(n.getAttribute("data-actorRoleId"))
            ));
        fetch("/actor-role/viewed", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({numbers: actorRolesIds})
        })
        .then(res => res.json())
        .then(data => console.log(data))
        .catch(err => console.error(err));

        notificationMenu.classList.toggle("d-none");
        notificationNumber.classList.add("d-none");
    });
}
document.addEventListener("click", e => {
    if(!bell.contains(e.target) && !notificationMenu.classList.contains("d-none")) {
        notificationMenu.classList.add("d-none");
    }
});




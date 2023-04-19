const bell = document.querySelector(".bell");
const notificationMenu = document.querySelector(".notification-menu");
const notificationNumber = document.querySelector(".notif-num");

bell.addEventListener("click", () => {
    notificationMenu.classList.toggle("d-none");
    notificationNumber.classList.add("d-none");
})
const account = document.querySelector(".account");
const accountMenu = document.querySelector(".account-menu");

account.addEventListener("click", () => {
   accountMenu.classList.toggle("d-none");
});

document.addEventListener("click", e => {
   if(!account.contains(e.target) && !accountMenu.classList.contains("d-none")) {
       accountMenu.classList.add("d-none");
   }
});
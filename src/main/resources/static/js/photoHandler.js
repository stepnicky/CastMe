const body = document.querySelector("body");
const images = document.querySelectorAll(".gallery ul .photo img");
const deleteBtns = document.querySelectorAll(".photo button.delete");
images.forEach(image => {
    image.addEventListener('click', function () {
        const src = this.getAttribute('src');
        const div = document.createElement('div');
        div.classList.add('fullScreen');
        const fullImg = document.createElement('img');
        fullImg.setAttribute('src', src);
        const exitBtn = document.createElement('button');
        exitBtn.textContent = 'Exit';
        exitBtn.classList.add('exit');
        div.append(fullImg);
        div.append(exitBtn);
        body.append(div);
        exitBtn.addEventListener('click', function () {
            this.closest(".fullScreen").remove();
        });
    });
})

deleteBtns.forEach(deleteBtn => {
    deleteBtn.addEventListener("click", function () {
        const img = this.nextElementSibling;
        const photoId = img.dataset.id;
        fetch(`/photo/${photoId}/delete`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => res.json())
            .then(res => console.log(res))
            .catch(err => console.log(err));
        img.classList.add("d-none");
        deleteBtn.classList.add("d-none");
    });
});
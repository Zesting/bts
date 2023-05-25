const viewAllImagesBtn = document.getElementById('view-all-images-btn');
const reviewImagesGrid = document.querySelector('.review-images-grid');
const reviewImages = document.querySelectorAll('.review-image');

viewAllImagesBtn.addEventListener('click', () => {
    const popup = window.open('', '', 'width=800,height=600');
    const popupContent = `
<style>
.popup-images-grid {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
.popup-image {
    margin-right: 10px;
    margin-bottom: 10px;
}
</style>
<div class="popup-images-grid">
${Array.from(reviewImages).map(reviewImage => `
<div class="popup-image">
  ${reviewImage.innerHTML}
</div>
`).join('')}
</div>
`;
    popup.document.write(popupContent);
});
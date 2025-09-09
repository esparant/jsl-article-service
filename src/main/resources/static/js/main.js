let currentScrollPosition = 0;
const scrollStep = 300;

function scrollChannels(direction) {
    const scrollContainer = document.getElementById('channelScroll');
    const maxScroll = scrollContainer.scrollWidth - scrollContainer.clientWidth;

    currentScrollPosition += direction * scrollStep;

    if (currentScrollPosition < 0) {
        currentScrollPosition = 0;
    } else if (currentScrollPosition > maxScroll) {
        currentScrollPosition = maxScroll;
    }

    scrollContainer.style.transform = `translateX(-${currentScrollPosition}px)`;

    // 버튼 표시/숨김 처리
    const leftBtn = document.querySelector('.scroll-btn-left');
    const rightBtn = document.querySelector('.scroll-btn-right');

    leftBtn.style.display = currentScrollPosition > 0 ? 'flex' : 'none';
    rightBtn.style.display = currentScrollPosition < maxScroll ? 'flex' : 'none';
}

// 초기 버튼 상태 설정
document.addEventListener('DOMContentLoaded', function () {
    const scrollContainer = document.getElementById('channelScroll');
    const leftBtn = document.querySelector('.scroll-btn-left');
    const rightBtn = document.querySelector('.scroll-btn-right');

    leftBtn.style.display = 'none';

    if (scrollContainer.scrollWidth <= scrollContainer.clientWidth) {
        rightBtn.style.display = 'none';
    }
});

const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
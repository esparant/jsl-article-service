document.addEventListener('DOMContentLoaded', () => {
	const containerSub = document.querySelector('.sort-box-sub');

	// container가 없으면 이벤트 리스너를 등록하지 않음
	if (!containerSub) return;

	containerSub.addEventListener('click', (event) => {
		const clickedButton = event.target.closest('.sub-remove');

		if (!clickedButton) {
			return;
		}

		const currentItem = clickedButton.closest('.sub-sortable');
		if (!currentItem) {
			return;
		}

		// ... (나머지 로직은 동일)
		if (clickedButton.classList.contains('sub-remove')) {
			currentItem.remove();
		}
	});


	// 
	new Sortable(containerSub, {
		handle: '.handle',
		animation: 150,
		ghostClass: "blue-background-class"
	});


	// 이모티콘 드래그 앤 드랍
	document.querySelectorAll(".sort-box-icon").forEach(sortIcon => {
		new Sortable(sortIcon, {
			filter: ".delete-button",
			group: "shared",
			animation: 150,
			ghostClass: "blue-background-class"
		});
	});
});

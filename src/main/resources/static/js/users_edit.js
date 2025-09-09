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
	
	
	// 삭제할 게시글 토글 기능
	const inputDeleteArticle = document.getElementById('article-delete'); // article-delete input 태그
	const selectedDeleteArticle = new Set(); // 중복을 허용하지 않는 set 객체 생성 > 삭제 할 id 저장용 객체
	
	function updateArticleInput() { // Set에 있는 모든 ID들을 쉼표로 구분된 문자열로 변환하여 input에 저장
		inputDeleteArticle.value = Array.from(selectedDeleteArticle).join(',');
	}
	
	document.querySelectorAll('[data-id]').forEach(link => { // data-article-id="" 속성이 있는 모든 요소에 반복
		link.addEventListener('click', (e) => { // 삭제하려는 게시글 클릭 시 기능
			e.preventDefault(); // 해당 요소의 기본 브라우저 동작 (링크 이동 등) 막아줌
			const id = link.dataset.id; // data-article-id="" 속성 가져옴
			const undoBtn = link.closest('.section-line').querySelector('.undo-btn'); // 제일 가까운 되돌리기 버튼 가져옴
			
			link.classList.add('on-delete'); // data-article-id 속성이 있는 html 요소에 class="active" 추가 > 게시글 빨갛고 진하게 변경
			undoBtn.classList.remove('d-none'); // 제일 가까운 되돌리기 버튼에 class="d-none" 삭제 > 되돌리기 버튼 생김
			
			selectedDeleteArticle.add(id); // set 객체에 id 추가
			updateArticleInput(); // 추가한 객체 input 태그에 적용
		})
	})
	
	document.querySelectorAll('.undo-btn').forEach(btn => { // class="undo-btn" 속성이 있는 모든 요소에 반복
		btn.addEventListener('click', (e) => { // 선택된 게시글 되돌리기 버튼 클릭 시 기능
			e.preventDefault(); // 해당 요소의 기본 브라우저 동작 (링크 이동 등) 막아줌
			const id = btn.dataset.id; // data-article-id="" 속성 가져옴
			const link = btn.closest('.section-line').querySelector('[data-id]'); // 제일 가까운 게시글 가져옴
			
			link.classList.remove('on-delete'); // 게시글의 class="active" 삭제 > 게시글 색,굵기 원래대로
			btn.classList.add('d-none'); // 버튼의 class="d-none" 추가 > 되돌리기 버튼 삭제
			
			selectedDeleteArticle.delete(id); // set 객체에서 id 제거
			updateArticleInput(); // 제거한 객체 input 태그에 적용
		})
	})
	
});

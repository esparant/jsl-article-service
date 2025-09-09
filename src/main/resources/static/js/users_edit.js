document.addEventListener('DOMContentLoaded', () => {
	const containerSub = document.querySelector('.sort-box-sub');

	// container가 없으면 이벤트 리스너를 등록하지 않음
	if (!containerSub) return;

	
	const inputDeleteSubscribe = document.getElementById('subscribe-delete'); // article-delete input 태그
	const setDeleteSubscribe = new Set(); // 중복을 허용하지 않는 set 객체 생성 > 삭제 할 id 저장용 객체
	
	document.querySelectorAll('#subscribe-list button.undo-btn').forEach(btn => { // #subscribe 밑에 .undo-btn 속성이 있는 모든 button 요소에 반복
		btn.addEventListener('click', (e) => { // 삭제하려는 게시글 클릭 시 기능
			if (btn.classList.contains('btn-outline-danger')) { // btn에 btn-outline-warning 클래스가 있을 때 실행될 코드
				e.preventDefault(); // 해당 요소의 기본 브라우저 동작 (링크 이동 등) 막아줌
				
				const id = btn.closest('.sub-sortable').querySelector('[data-id]').dataset.id;
				
				btn.classList.add('btn-outline-secondary-emphasis');
				btn.classList.remove('btn-outline-danger');
				
				setDeleteSubscribe.delete(id); // set 객체에 id 삭제
				inputDeleteSubscribe.value = Array.from(setDeleteSubscribe).join(','); // 삭제한 객체 input 태그에 적용
			} else if (btn.classList.contains('btn-outline-secondary-emphasis')) { // btn에 btn-outline-secondary-emphasis 클래스가 있을 때 실행될 코드
				e.preventDefault(); // 해당 요소의 기본 브라우저 동작 (링크 이동 등) 막아줌
				
				const id = btn.closest('.sub-sortable').querySelector('[data-id]').dataset.id;
				
				btn.classList.add('btn-outline-danger');
				btn.classList.remove('btn-outline-secondary-emphasis');
				
				setDeleteSubscribe.add(id); // set 객체에 id 추가
				inputDeleteSubscribe.value = Array.from(setDeleteSubscribe).join(','); // 추가한 객체 input 태그에 적용
			}
		})
	})

	// 
	new Sortable(containerSub, {
		handle: '.handle',
		animation: 150,
		ghostClass: "blue-background-class"
	});

	const inputDeleteEmoji = document.getElementById('emoji-delete');
	const setDeleteEmoji = new Set();
	// 이모티콘 드래그 앤 드랍
	document.querySelectorAll(".sort-box-emoji").forEach(sortEmoji => {
		new Sortable(sortEmoji, {
			filter: ".delete-button",
			group: "shared",
			animation: 150,
			ghostClass: "blue-background-class",
			
			onEnd: function (evt) {
				const draggedItem = evt.item; // 드랍된 요소
				
				if (evt.to.id === 'emoji-delete') { // 드랍된 목적지가 #emoji-delete 였다면
					const id = draggedItem.dataset.id;
					
					setDeleteEmoji.add(id); // set 객체에 id 추가
					inputDeleteEmoji.value = Array.from(setDeleteEmoji).join(','); // 추가한 객체 input 태그에 적용
				} else {
					const id = draggedItem.dataset.id;
					
					setDeleteEmoji.delete(id); // set 객체에 id 추가
					inputDeleteEmoji.value = Array.from(setDeleteEmoji).join(','); // 추가한 객체 input 태그에 적용
				}
			}
		});
	});


	// 삭제할 게시글 토글 기능
	const listArticle = '#article-list'; // article 목록 선택자
	const inputDeleteArticle = document.getElementById('article-delete'); // article-delete input 태그
	const setDeleteArticle = new Set(); // 중복을 허용하지 않는 set 객체 생성 > 삭제 할 id 저장용 객체
	const listComment = '#comment-list'; // article 목록 선택자
	const inputDeleteComment = document.getElementById('comment-delete');
	const setDeleteComment = new Set();
	const listScrap = '#scrap-list';
	const inputDeleteScrap = document.getElementById('scrap-delete');
	const setDeleteScrap = new Set();


	function destinyDelete(input, selectSet, list) {
		const listAddAnchor = `${list} a[data-id]`;

		document.querySelectorAll(listAddAnchor).forEach(link => { // data-article-id="" 속성이 있는 모든 요소에 반복
			link.addEventListener('click', (e) => { // 삭제하려는 게시글 클릭 시 기능
				e.preventDefault(); // 해당 요소의 기본 브라우저 동작 (링크 이동 등) 막아줌
				const id = link.dataset.id; // data-article-id="" 속성 가져옴
				const undoBtn = link.closest('.section-line').querySelector('.undo-btn'); // 제일 가까운 되돌리기 버튼 가져옴

				link.classList.add('on-delete'); // data-article-id 속성이 있는 html 요소에 class="active" 추가 > 게시글 빨갛고 진하게 변경
				undoBtn.classList.remove('d-none'); // 제일 가까운 되돌리기 버튼에 class="d-none" 삭제 > 되돌리기 버튼 생김

				selectSet.add(id); // set 객체에 id 추가
				input.value = Array.from(selectSet).join(','); // 추가한 객체 input 태그에 적용
			})
		})

	}
	function cancelDelete(input, selectSet, list) {
		const listAddUndo = `${list} button.undo-btn`;

		document.querySelectorAll(listAddUndo).forEach(btn => { // data-article-id="" 속성이 있는 모든 요소에 반복
			btn.addEventListener('click', (e) => { // 선택된 게시글 되돌리기 버튼 클릭 시 기능
				e.preventDefault(); // 해당 요소의 기본 브라우저 동작 (링크 이동 등) 막아줌
				const id = btn.dataset.id; // data-article-id="" 속성 가져옴
				const link = btn.closest('.section-line').querySelector('[data-id]'); // 제일 가까운 게시글 가져옴

				link.classList.remove('on-delete'); // 게시글의 class="active" 삭제 > 게시글 색,굵기 원래대로
				btn.classList.add('d-none'); // 버튼의 class="d-none" 추가 > 되돌리기 버튼 삭제

				selectSet.delete(id); // set 객체에서 id 제거
				input.value = Array.from(selectSet).join(','); // 삭제한 객체 input 태그에 적용
			})
		})

	}

	destinyDelete(inputDeleteArticle, setDeleteArticle, listArticle);
	cancelDelete(inputDeleteArticle, setDeleteArticle, listArticle);
	destinyDelete(inputDeleteComment, setDeleteComment, listComment);
	cancelDelete(inputDeleteComment, setDeleteComment, listComment);
	destinyDelete(inputDeleteScrap, setDeleteScrap, listScrap);
	cancelDelete(inputDeleteScrap, setDeleteScrap, listScrap);

});

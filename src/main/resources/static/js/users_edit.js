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

	// 구독목록 드래그 앤 드랍
	let isOrderChangedSub = false; // 구독 목록 순서 변경 여부 확인용
	new Sortable(containerSub, {
		handle: '.handle',
		animation: 150,
		ghostClass: "blue-background-class",
		onEnd: function(evt) {
			const subBoards = containerSub.querySelectorAll('button[data-sub-order]');

			for (let i = 0; i < subBoards.length; i++) {
				const currentSub = subBoards[i];
				const newOrder = i + 1;

				currentSub.dataset.subOrder = newOrder;
			}

			isOrderChangedSub = true; // 구독 목록 순서 변경됐으니 true
		}
	});

	// 이모티콘 드래그 앤 드랍
	const inputDeleteEmoji = document.getElementById('emoji-delete-input');
	const setDeleteEmoji = new Set();
	let isOrderChangedEmoji = false; // 이모티콘 순서 변경 여부 확인용

	const sortableEmojiContainer = document.querySelectorAll('.sort-box-emoji');

	function updateEmojiOrder(emojiContainerId) { // 이모티콘 순서 바꾸는 함수
		const emojiContainer = document.getElementById(emojiContainerId);

		if (!emojiContainer) return;

		const emojis = emojiContainer.querySelectorAll('img[data-id]');

		for (let i = 0; i < emojis.length; i++) {
			const emoji = emojis[i];
			const newOrder = i + 1;

			if (emojiContainerId === 'emoji-used') {
				emoji.dataset.emojiUsed = newOrder;
			} else if (emojiContainerId === 'emoji-owned') {
				emoji.dataset.emojiOwned = newOrder;
			}
		}
	}
	sortableEmojiContainer.forEach(sortEmoji => {
		new Sortable(sortEmoji, {
			filter: ".delete-button",
			group: "shared",
			animation: 150,
			ghostClass: "blue-background-class",

			onEnd: function(evt) {
				const draggedItem = evt.item; // 드랍된 요소
				const fromContainerId = evt.from.id;
				const toContainerId = evt.to.id;

				delete draggedItem.dataset.emojiUsed;
				delete draggedItem.dataset.emojiOwned;

				if (evt.to.id === 'emoji-delete') { // 드랍된 목적지가 #emoji-delete 였다면
					const id = draggedItem.dataset.id;
					setDeleteEmoji.add(id); // set 객체에 id 추가
					inputDeleteEmoji.value = Array.from(setDeleteEmoji).join(','); // 추가한 객체 input 태그에 적용
				} else if (evt.to.id === 'emoji-used') {
					draggedItem.dataset.emojiUsed = "";
				} else if (evt.to.id === 'emoji-owned') {
					draggedItem.dataset.emojiOwned = "";
				}
				updateEmojiOrder(fromContainerId);
				updateEmojiOrder(toContainerId);
				isOrderChangedEmoji = true; // 이모티콘 순서 변경됐으니 true
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

	// 차단유저 해제 토글 기능
	const inputDeleteBlock = document.getElementById('block-delete');
	const setDeleteBlock = new Set();


	document.querySelectorAll('#block-list button[data-id]').forEach(btn => { // #block-list 자식중에 data-id="" 속성이 있는 모든 button에 반복
		btn.addEventListener('click', (e) => {
			if (btn.classList.contains('btn-outline-secondary')) { // btn-outline-secondary 클래스가 있을 때 실행될 코드
				e.preventDefault(); // 해당 요소의 기본 브라우저 동작 (링크 이동 등) 막아줌

				const id = btn.dataset.id;

				btn.classList.remove('btn-outline-secondary');
				btn.classList.add('btn-secondary');

				setDeleteBlock.add(id);
				inputDeleteBlock.value = Array.from(setDeleteBlock).join(',');
			} else if (btn.classList.contains('btn-secondary')) { // btn에 btn-outline-secondary-emphasis 클래스가 있을 때 실행될 코드
				e.preventDefault(); // 해당 요소의 기본 브라우저 동작 (링크 이동 등) 막아줌

				const id = btn.dataset.id;

				btn.classList.remove('btn-secondary');
				btn.classList.add('btn-outline-secondary');

				setDeleteBlock.delete(id);
				inputDeleteBlock.value = Array.from(setDeleteBlock).join(',');
			}
		})
	})

	let selectedExpiredEmojiId = null;
	document.querySelectorAll('#emoji-expired img').forEach(img => {
		img.addEventListener('click', (e) => {
			selectedExpiredEmojiId = img.dataset.id;
		})
	})

	document.querySelector('#emojiPurchase').addEventListener('click', (e) => {
		e.preventDefault();
		const emojiPurchasedUrl = '#'; // 이모티콘 구매요청할 URL
		if (selectedExpiredEmojiId) {

			const requestData = {
				id: selectedExpiredEmojiId // 서버로 보낼 데이터
			};

			fetch(emojiPurchasedUrl, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(requestData)
			}).then(response => {
				// 서버 응답이 성공(200 OK)이 아니면 에러를 발생시킵니다.
				if (!response.ok) {
					throw new Error('서버 응답 오류: ' + response.statusText);
				}
				return response.json(); // 서버 응답을 JSON으로 파싱
			}).then(data => {
				// 서버로부터 성공적인 응답을 받았을 때 실행
				console.log('서버 응답:', data);
				console.log(successMessage); // 성공 메시지 표시

				// ----------------------------------------------------
				// 비동기 통신 성공 후 HTML 요소 추가하는 부분
				// ----------------------------------------------------

				// 1. 서버 응답 데이터에서 필요한 정보 추출
				const newEmojiId = data.newId; // 서버에서 받은 새로운 ID
				const newEmojiSrc = data.newSrc; // 서버에서 받은 새로운 이미지 경로
				const newEmojiOrder = data.newSrc; // 서버에서 받은 새로운 이미지 순서

				// 2. 새로운 img 태그 생성
				const newImg = document.createElement('img');

				// 3. 생성된 img 태그에 속성 설정
				newImg.src = newEmojiSrc;
				newImg.dataset.id = newEmojiId; // data-id 속성 추가
				newImg.dataset.emojiUsed = newEmojiOrder;
				newImg.classList.add('grid-item', 'emoji-used', 'm-2'); // 클래스 추가
				newImg.alt = "이모티콘";

				// 4. 특정 부모 요소에 새로운 img 태그 추가
				const parentElement = document.querySelector('#emoji-used'); // 이모티콘을 추가할 부모 요소
				if (parentElement) {
					parentElement.appendChild(newImg);
				} else {
					console.error('부모 요소를 찾을 수 없습니다.');
				}

				// 모달을 닫습니다.
				bootstrap.Modal.getInstance(document.getElementById('emojiPurchaseModal')).hide();

				// TODO: 필요한 경우, 성공 후 HTML 요소를 동적으로 추가하는 로직을 여기에 작성하세요.
			}).catch(error => {
				// 네트워크 오류 또는 서버 응답 오류가 발생했을 때 실행
				console.error('구매 요청 실패:', error);
				console.log('구매에 실패했습니다. 다시 시도해 주세요.');
			});
			bootstrap.Modal.getInstance(document.getElementById('emojiPurchaseModal')).hide();
		} else {
			console.log("선택된 이미지가 없습니다. 구매 실패.");
		}
	})
	
	
	// 폼 가로채고 구독, 이모티콘 순서 값 추가 후 전송
	const customForm = document.getElementById('userEdit'); // 폼 id 변수에 담기
	console.log(customForm);
	customForm.addEventListener('submit', function(e) { // 폼 이벤트 리스너 추가
		e.preventDefault(); // 기본 submit 동작 방지

		const formData = new FormData(customForm); // 폼 데이터 변수에 담기
		const combinedData = {}; // 기존 폼 데이터와 병합할 객체

		if (isOrderChangedEmoji) {
		const usedEmojis = []; // 사용중인 이모티콘 순서 객체
		const usedEmojiElements = document.querySelectorAll('#emoji-used img[data-emoji-used]');
		usedEmojiElements.forEach(img => {
			usedEmojis.push({
				id: img.dataset.id,
				sortOrder: img.dataset.emojiUsed
			});
		});
		combinedData.emojiUsed = usedEmojis;
		const ownedEmojis = [];
		const ownedEmojiElements = document.querySelectorAll('#emoji-owned img[data-emoji-owned]');
		ownedEmojiElements.forEach(img => {
			ownedEmojis.push({
				id: img.dataset.id,
				sortOrder: img.dataset.emojiOwned
			});
		});
		combinedData.emojiOwned = ownedEmojis;
		}


		if (isOrderChangedSub) {
			const subs = [];
			const subsElements = document.querySelectorAll('#subscribe-list button[data-sub-order]');
			subsElements.forEach(btn => {
				subs.push({
					id: btn.dataset.id,
					sortOrder: btn.dataset.subOrder
				});
			});
			combinedData.subsOrder = subs;
		}
		for (const [key, value] of formData.entries()) {
			combinedData[key] = value;
		}

		const userEditUrl = '#';

		fetch(userEditUrl, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(combinedData),
		})
			.then(response => response.json())
			.then(data => {
				console.log('성공 : ', data);
				console.log(combinedData);
				// 페이지 이동 후 추가 로직 작성하세요 (페이지 이동 등)
			})
			.catch(error => {
				console.log('실패 : ', error);
			})

	})
});

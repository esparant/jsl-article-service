// 인기 이모티콘과 세트 탭의 첫 번째 버튼(일간)을 기본 활성화
$('.popular-emoticon-tabs .btn:first-child').addClass('active');
$('.popular-emoticon-sets-tabs .btn:first-child').addClass('active');

// 인기 이모티콘과 세트 탭의 각 버튼 클릭 시 이벤트 처리
// 같은 그룹의 모든 버튼에서 'active' 클래스 제거와 동시에 클릭된 버튼에 'active' 클래스 추가
$('.popular-emoticon-tabs .btn').click(function() {
	$(this).siblings().removeClass('active');
	$(this).addClass('active');
});
$('.popular-emoticon-sets-tabs .btn').click(function() {
	$(this).siblings().removeClass('active');
	$(this).addClass('active');
});

// 탭에 따라 변경될 더미 데이터 정의
// 실제 프로젝트에서는 서버 API를 통해 데이터를 받아옵니다.
const popularEmojis = {
    daily: [
        { name: '도로롱1', description: '도롱도롱', sales: 250, points: 10, image: '/img/uploads/emoji/dororong1.gif' },
        { name: '도로롱2', description: '도롱도롱', sales: 230, points: 10, image: '/img/uploads/emoji/dororong2.gif' },
        { name: '도로롱3', description: '도롱도롱', sales: 210, points: 10, image: '/img/uploads/emoji/dororong3.gif' },
        { name: '도로롱4', description: '도롱도롱', sales: 205, points: 10, image: '/img/uploads/emoji/dororong4.gif' },
        { name: '도로롱5', description: '도롱도롱', sales: 190, points: 10, image: '/img/uploads/emoji/dororong5.gif' }
    ],
    weekly: [
        { name: '도로롱6',	description: '도롱도롱', sales: 500, points: 15, image: '/img/uploads/emoji/dororong5.gif' },
        { name: '도로롱7',	description: '도롱도롱', sales: 480, points: 15, image: '/img/uploads/emoji/dororong4.gif' },
        { name: '도로롱8',	description: '도롱도롱', sales: 450, points: 15, image: '/img/uploads/emoji/dororong3.gif' },
        { name: '도로롱9',	description: '도롱도롱', sales: 430, points: 15, image: '/img/uploads/emoji/dororong2.gif' },
        { name: '도로롱10', description: '도롱도롱', sales: 410, points: 15, image: '/img/uploads/emoji/dororong1.gif' }
    ],
    monthly: [
        { name: '도로롱11', description: '도롱도롱', sales: 1200, points: 20, image: '/img/uploads/emoji/dororong1.gif' },
        { name: '도로롱12', description: '도롱도롱', sales: 1150, points: 20, image: '/img/uploads/emoji/dororong2.gif' },
        { name: '도로롱13', description: '도롱도롱', sales: 1100, points: 20, image: '/img/uploads/emoji/dororong3.gif' },
        { name: '도로롱14', description: '도롱도롱', sales: 1050, points: 20, image: '/img/uploads/emoji/dororong4.gif' },
        { name: '도로롱15', description: '도롱도롱', sales: 1000, points: 20, image: '/img/uploads/emoji/dororong5.gif' }
    ]
};

const popularEmojiSets = {
    daily: [
        { name: '도로롱6',	description: '도롱도롱', sales: 500, points: 15, image: '/img/uploads/emoji/dororong5.gif' },
        { name: '도로롱7',	description: '도롱도롱', sales: 480, points: 15, image: '/img/uploads/emoji/dororong4.gif' },
        { name: '도로롱8',	description: '도롱도롱', sales: 450, points: 15, image: '/img/uploads/emoji/dororong3.gif' },
        { name: '도로롱9',	description: '도롱도롱', sales: 430, points: 15, image: '/img/uploads/emoji/dororong2.gif' },
        { name: '도로롱10', description: '도롱도롱', sales: 410, points: 15, image: '/img/uploads/emoji/dororong1.gif' }
    ],
    weekly: [
        { name: '도로롱11', description: '도롱도롱', sales: 1200, points: 20, image: '/img/uploads/emoji/dororong1.gif' },
        { name: '도로롱12', description: '도롱도롱', sales: 1150, points: 20, image: '/img/uploads/emoji/dororong2.gif' },
        { name: '도로롱13', description: '도롱도롱', sales: 1100, points: 20, image: '/img/uploads/emoji/dororong3.gif' },
        { name: '도로롱14', description: '도롱도롱', sales: 1050, points: 20, image: '/img/uploads/emoji/dororong4.gif' },
        { name: '도로롱15', description: '도롱도롱', sales: 1000, points: 20, image: '/img/uploads/emoji/dororong5.gif' }
    ],
    monthly: [
        { name: '도로롱1', description: '도롱도롱', sales: 250, points: 10, image: '/img/uploads/emoji/dororong1.gif' },
        { name: '도로롱2', description: '도롱도롱', sales: 230, points: 10, image: '/img/uploads/emoji/dororong2.gif' },
        { name: '도로롱3', description: '도롱도롱', sales: 210, points: 10, image: '/img/uploads/emoji/dororong3.gif' },
        { name: '도로롱4', description: '도롱도롱', sales: 205, points: 10, image: '/img/uploads/emoji/dororong4.gif' },
        { name: '도로롱5', description: '도롱도롱', sales: 190, points: 10, image: '/img/uploads/emoji/dororong5.gif' }
    ]
};

// HTML 템플릿 함수: 이모티콘 데이터를 받아서 HTML 코드를 생성합니다.
const createEmojiCard = (emoji) => {
	// 백틱(``)을 사용하여 자바스크립트 변수를 HTML 코드 안에 넣을 수 있습니다.
    return `
        <div class="col">
            <div class="card text-center h-100">
                <img src="${emoji.image}" alt="이모티콘 사진" class="card-img-top p-2">
                <div class="card-body p-2">
                    <h6 class="card-title fw-bold">${emoji.name}</h6>
                    <p class="card-text mb-0">${emoji.description}</p>
                    <p class="card-text mb-0">
                        <small class="text-muted">판매수: ${emoji.sales}</small>
                    </p>
                    <p class="card-text mb-2">
                        <small class="text-muted">포인트: ${emoji.points}p</small>
                    </p>
                    <a href="#" class="btn btn-primary">구매</a>
                </div>
            </div>
        </div>
    `;
};

// 데이터를 화면에 렌더링하는 함수
// 배열과 컨테이너 선택자를 이용해 비우고 채우는 방식
// data: 이모티콘 데이터 배열입니다.
// containerSelector: 데이터를 넣을 컨테이너의 선택자입니다.
const renderEmojis = (data, containerSelector) => {
    const container = $(containerSelector);
    container.empty(); // 기존 내용을 비웁니다.
    const cardsHtml = data.map(createEmojiCard).join('');
	// map(): 배열의 각 요소에 대해 createEmojiCard 함수를 호출하여 새로운 배열을 만듭니다.
	// join(''): 함수는 배열의 모든 요소를 하나의 문자열로 합칩니다.
    container.append(cardsHtml); // 새로운 내용을 추가합니다.
};

// 초기 로딩 시 일간 데이터 렌더링(문서가 준비되면 실행)
$(document).ready(function() {
    renderEmojis(popularEmojis.daily, '#popularEmojisContainer');
    renderEmojis(popularEmojiSets.daily, '#popularEmojiSetsContainer');
});

// 인기 이모티콘과 세트 탭 클릭 시 데이터 변경
$('.popular-emoticon-tabs .btn').click(function() {
    $(this).siblings().removeClass('active');
    $(this).addClass('active');

    // 클릭된 버튼의 텍스트를 기준으로 데이터를 선택
    // '일간', '주간', '월간' 중 하나을 가져옴
    const period = $(this).text().trim();
    let dataToRender;

    // period 값에 따라 상기 popularEmojis 변수 중에서 적절한 데이터를 선택
    // 일간 = daily, 주간 = weekly, 월간 = monthly
    if (period === '일간') {
        dataToRender = popularEmojis.daily;
    } else if (period === '주간') {
        dataToRender = popularEmojis.weekly;
    } else if (period === '월간') {
        dataToRender = popularEmojis.monthly;
    }

    renderEmojis(dataToRender, '#popularEmojisContainer');
    // renderEmojis 함수를 호출하여 데이터를 렌더링
});

$('.popular-emoticon-sets-tabs .btn').click(function() {
    $(this).siblings().removeClass('active');
    $(this).addClass('active');

    const period = $(this).text().trim();
    let dataToRender;

    if (period === '일간') {
        dataToRender = popularEmojiSets.daily;
    } else if (period === '주간') {
        dataToRender = popularEmojiSets.weekly;
    } else if (period === '월간') {
        dataToRender = popularEmojiSets.monthly;
    }

    renderEmojis(dataToRender, '#popularEmojiSetsContainer');
});
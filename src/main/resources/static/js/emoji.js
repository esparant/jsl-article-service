// 인기 이모티콘 탭
$('.popular-emoticon-tabs .btn:first-child').addClass('active');

// 인기 이모티콘 세트 탭
$('.popular-emoticon-sets-tabs .btn:first-child').addClass('active');

// 인기 이모티콘 탭 버튼 클릭 이벤트 처리
$('.popular-emoticon-tabs .btn').click(function() {
	// 현재 그룹의 모든 버튼에서 'active' 클래스 제거
	$(this).siblings().removeClass('active');
	// 클릭된 버튼에 'active' 클래스 추가
	$(this).addClass('active');
});

// 인기 이모티콘 세트 탭 버튼 클릭 이벤트 처리
$('.popular-emoticon-sets-tabs .btn').click(function() {
	// 현재 그룹의 모든 버튼에서 'active' 클래스 제거
	$(this).siblings().removeClass('active');
	// 클릭된 버튼에 'active' 클래스 추가
	$(this).addClass('active');
});
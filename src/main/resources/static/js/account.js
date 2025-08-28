function showSocialButtons(){
	const buttons = getButtons();
	buttons.socialButton.setAttribute("hidden", true);
	buttons.memberButton.setAttribute("hidden", true);
	buttons.socialButtons.forEach(button => {
		button.removeAttribute("hidden");
	})
}

function hideSocialButtons() {
	const buttons = getButtons();
	buttons.socialButton.removeAttribute("hidden");
	buttons.memberButton.removeAttribute("hidden");
	buttons.socialButtons.forEach(button => {
		button.setAttribute("hidden", true);
	})
}

function getButtons() {
	const socialButton = document.querySelector("#social");
	const memberButton = document.querySelector("#member");
	const socialButtons = document.querySelectorAll("#google, #kakao, #naver, #back");
	return { socialButton, memberButton, socialButtons }
}

(() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()

function agree(input){
	const agree = input.value;
	
	if (agree == "on") {
		location.href = "signup";
	}
}

function sendConfirmCode() {
	const confirmLine = document.querySelector(".confirmLine");
	confirmLine.removeAttribute("hidden");
	/**
	 * TODO: 이 아래로 이메일 보내는 코드
	 */
}

function verifyConfirmCode() {
	/**
	 * 신나게 이메일 인증코드 검사하는 코드
	 */
	alert("인증되었습니다.");
}

function signup() {
	/**
	 * 회원가입 로직 후 환영합니다.
	 */
	const nickname = document.querySelector("#nickname").value || "익명"
	alert(`${nickname}님 환영합니다.`);
}

function sendRepasswordLink(event) {
	event.preventDefault();
	const form = event.target;
	if (!form.checkValidity()) return;
	/**
	 * 신나는 이메일 링크 관련 보내는 로직
	 */
	const message = "입력하신 이메일로 비밀번호 변경링크를 보냈습니다.";
	const inputElement = document.querySelector(".inputBox");
	const messageElement = document.querySelector(".messageBox");

	inputElement.setAttribute("hidden", true);
	messageElement.classList.add("text-success");
	messageElement.innerHTML = message;
}

function changePassword(event) {
	event.preventDefault();
	const form = event.target;
	const message = "비밀번호가 변경되었습니다.";

	if (!form.checkValidity()) return;
	alert(message);
	location.href = "login";
}
function writeCommentToggle(textarea) {
	const { classList } = document.querySelector("#writeComment");
	if (textarea.value) {
		classList.remove("d-none")
	} else {
		classList.add("d-none")
	}
}
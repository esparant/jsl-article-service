package com.projects.jslarticle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TemplateGlobalViewerController
 * 모든 HTML Template 파일을 단일 엔드포인트로 서빙하는 컨트롤러
 * 
 * @author MJ2
 * @since 2025.08.22
 * 
 * @note
 * - 개발용/프론트 전용
 * - favicon 요청 무시
 * - TODO: 해야할 일을 다 했기 때문에 비활성화 합니다.
 * 
 * @usage
 * 	GET /index -> "templates/index.html" 뷰 반환
 *  GET /good/morning -> "templates/good/morning.html" 뷰 반환
 */
// @Controller // 비활성화
@RequestMapping("/")
public class TemplateGlobalViewerController {

	/**
	 * favicon 요청 무시용
	 */
	@GetMapping("favicon.ico")
	@ResponseBody
	String favicon(){ return ""; }
	
	@GetMapping("{filename}")
	String show(@PathVariable("filename") String filename) {
		return filename;
	}
	/**
	 * @author MJ2
	 * @modifier 정규진
	 * @date 2025-09-02
	 * @version 1.2
	 * @description folder/*.html 형식으로 따로 컨트롤러 없이 바로 html를 볼 수 있도록 도와주는 함수
	 * 
	 * @history v1.0 - 2025-08-10: 최초 작성 (MJ2)
	 * @history v1.1 - 2025-09-02: 다른 패키지도 볼 수 있도록 메소드 추가
	 * v1.1 - 2025-09-02: show 함수가 account 템플릿 패키지에만 작동하여 showAccount, showUsers 두개의 함수로 나눈 뒤 각 패키지별로 맵핑하도록 수정
	 * @history v1.2 - 2025-09-03: 메소드 추가 방식이 아닌 GetMapping 어노테이션에 파이프라인 구분자로 파일 추가 및 메소드명 원복 {folder:account|users|board|...}
	 */
	@GetMapping("{folder:account|board|users}/{filename}")
	String show(@PathVariable("folder") String folder, @PathVariable("filename") String filename) {

		return folder + "/" + filename;
	}
}

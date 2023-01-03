package customer.build.controller;

import customer.build.domain.Member;
import customer.build.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    // 생성자로 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 회원 가입으로 매핑
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")    // post 방식의 요청을 받아 파라미터를 MemberForm 에 담겨준다.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    // 회원 조회
    @GetMapping("/members")
    public String list(Model model) {   // List 형태로 담아 members 파일 아래 memberList 파일에 출력
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}

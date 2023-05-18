package Hello.hellospring.controller;

import Hello.hellospring.domain.Member;
import Hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 컨트롤러 annotation이 있으면 스프링 뜰대 객체를 생성해 Container에 넣어둔다
@Controller
public class MemberController {

    private final MemberService memberService;


    // Spring Container에서 memberService를 가져온다. 중복해서 new로 생성할 필요 없음!!
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createFrom(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        
        // 단축키 ! sout, soutv Print문 호출
        System.out.println("member.getName() = " + member.getName());
        
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

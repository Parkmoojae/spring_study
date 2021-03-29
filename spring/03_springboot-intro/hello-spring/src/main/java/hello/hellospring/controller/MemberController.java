package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

//    1. Constructor Injection (Recommended)
   @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

//    2. Field Injection
//    @Autowired
//    private MemberService memberService;


//    3. Setter Injection
//    private MemberRepository memberRepository;
//    @Autowired
//    public void setMemberService(MemberService memberService){
//       this.memberService = memberService;
//    }
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

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

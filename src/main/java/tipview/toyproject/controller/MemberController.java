package tipview.toyproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tipview.toyproject.Service.MemberService;
import tipview.toyproject.Service.ReviewService;
import tipview.toyproject.domain.Member;
import tipview.toyproject.domain.Review;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/signIn")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/signIn")
    @ResponseBody
    public String create(@RequestBody @Valid MemberForm form, BindingResult result) {

        //@Valid를 통한 유효성 검사의 결과가 담김
        if(result.hasErrors()) {
            return "SingInFail";
        }

        Member member = new Member();
        member.setNickname(form.getNickName());
        member.setPassword(form.getPassword());
        memberService.Join(member);

        return "SignInOk";
    }
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody MemberForm form) {
        String login = memberService.Login(form.getNickName(),form.getPassword());


        if(login == "true"){
            return "Login Success";
        }
        else {
            return "Login Failed";
        }
    }






}
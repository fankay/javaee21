package com.kaishengit.controller;

import com.kaishengit.pojo.Notice;
import com.kaishengit.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Inject
    private NoticeService noticeService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "notice/list";
    }

    /**
     * 发表公告
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newNotice() {
        return "notice/new";
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String newNotice(Notice notice, RedirectAttributes redirectAttributes) {
        noticeService.saveNotice(notice);
        redirectAttributes.addFlashAttribute("message","发表成功");
        return "redirect:/notice";
    }

}

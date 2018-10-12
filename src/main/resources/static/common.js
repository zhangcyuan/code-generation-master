/*
 @author:
 */

//首页轮播图
var gallery = mui('#home-slider');
gallery.slider({
    interval: 4000
});


// 书架页面JS
$(".w-cleanUp").click(function() {
    var active1 = $("#item1").hasClass("mui-active");
    var active2 = $("#item2").hasClass("mui-active");
    var checkbox1 = $(".w-checkbox1 input[name='checked']");
    var checkbox2 = $(".w-checkbox2 input[name='checked']");
    var SelectAll1 = $(".w-operation1 .w-SelectAll"),
        SelectAll2 = $(".w-operation2 .w-SelectAll");
    var SelectNone1 = $(".w-operation1 .w-SelectNone"),
        SelectNone2 = $(".w-operation2 .w-SelectNone");
    $(".mui-control-item.w-active").addClass("mui-disabled ");
    $(".mui-control-item.w-active i").addClass("mui-disabled ");


    var imgWidth=$(".w-bookPic img").width();
    var imgHeight=$(".w-bookPic img").height();
    $(".w-checkbox1").css({"width":imgWidth+"px","height":imgHeight+"px"});
    $(".w-checkbox1 input").css({"width":imgWidth+"px","height":imgHeight+"px"});


    var imgHeight2=$(".w-Listmargin img").height();
    $(".w-checkbox2").css({"width":100+"%","height":imgHeight+"px"});
    $(".w-checkbox2 label").css({"width":100+"%","height":imgHeight+"px"});




    if(active1) {
        $(".w-cleanUp").hide();
        $(".mui-active .w-checkbox1,.w-achieve").show();
        $(".w-bookPic img").addClass("bg-shade");
        $(".mui-active .w-operation1").slideDown("fast");

        SelectAll1.click(function() {
            checkbox1.prop("checked", true);
            $(".w-delete").addClass("colorf6");
            SelectAll1.hide();
            SelectNone1.show();
        });
        SelectNone1.click(function() {
            checkbox1.prop("checked", false);
            $(".w-delete").removeClass("colorf6");
            SelectAll1.show();
            SelectNone1.hide();
        });

        checkbox1.change(function() {
            var flag = 1; //默认全选状态
            checkbox1.each(function() {
                if(!$(this).prop("checked")) {
                    flag = 0;
                }
            });
            if(flag) {
                SelectAll1.hide();
                SelectNone1.show();
            } else {
                SelectAll1.show();
                SelectNone1.hide();
                $(".w-delete").removeClass("colorf6");
            }
            var checked = $(".w-checkbox1 input[name='checkbox']:checked");
            var checkedAll = $(".w-checkbox1 input[name='checkbox']");
            var len = checked.length;
            if(len>0){
                $(".w-delete").addClass("colorf6");

            }else {
                $(".w-delete").removeClass("colorf6");
            }


        });



    } else if(active2) {
        $(".w-cleanUp").hide();
        $(".w-achieve,.mui-active .w-checkbox2").show();
        $(".w-historyList a").addClass("w-Listmargin");
        $(".mui-active .w-operation2").slideDown("fast");
        SelectAll2.click(function() {
            checkbox2.prop("checked", true);
            $(".w-delete").addClass("colorf6");
            SelectAll2.hide();
            SelectNone2.show();
        });
        SelectNone2.click(function() {
            checkbox2.prop("checked", false);
            $(".w-delete").removeClass("colorf6");
            SelectAll2.show();
            SelectNone2.hide();

        });
        checkbox2.change(function() {
            var flag = 1; //默认全选状态
            checkbox2.each(function() {
                if(!$(this).prop("checked")) {
                    flag = 0;
                }
            });
            if(flag) {
                SelectAll2.hide();
                SelectNone2.show();
            } else {
                SelectAll2.show();
                SelectNone2.hide();
            }
            var checked = $(".w-checkbox2 input[name='checkbox']:checked");
            var checkedAll = $(".w-checkbox2 input[name='checkbox']");
            var len = checked.length;
            if(len>0){
                $(".w-delete").addClass("colorf6");

            }else {
                $(".w-delete").removeClass("colorf6");
            }
        });

    }


    //点击完成按钮
    $(".w-achieve").click(function() {
        $(".w-checkbox1,.w-achieve,.w-checkbox2").hide();
        $(".w-cleanUp").show();
        $(".w-historyList a").removeClass("w-Listmargin");
        $(".mui-control-item.w-active").removeClass("mui-disabled");
        $(".mui-control-item.w-active i").removeClass("mui-disabled ");
        checkbox1.attr("checked", false);
        checkbox2.prop("checked", false);
        $(".w-operation1 .w-SelectAll,.w-operation2 .w-SelectAll").show();
        $(".w-operation1 .w-SelectNone,.w-operation2 .w-SelectNone").hide();
        $(".mui-active .w-operation1,.mui-active .w-operation2").slideUp("fast");
    });
});




//个人中心发表收藏整理
$(".common-cleanUp").click(function() {
    var checkbox3 = $(".com-checkbox3 input[name='checkbox']");
    $(".community-tab a").addClass("mui-disabled ");
    $(".com-achieve").show();
    $(".common-cleanUp").hide();
    var arr=[];

    $('.community-recommend-dd').each(function(i){
        var imgHeight3=$(this).height();
        arr.push(imgHeight3);
        $(this).find(".com-checkbox3").css({"width":100+"%","height":arr[i]+30+"px"});
        $(this).find(".com-checkbox3 label").css({"width":100+"%","height":arr[i]+30+"px"});
    });

    $(".com-operation3").slideDown("fast");
    $(".com-checkbox3").show();
    $(".com-list-main").addClass("w-Listmargin");

    checkbox3.change(function() {
        var checked = $(".com-checkbox3 input[name='checkbox']:checked");
        var len = checked.length;
        if(len>0){
            $(".com-delete").addClass("colorf6");

        }else {
            $(".com-delete").removeClass("colorf6");
        }
    });

    $(".com-achieve").click(function() {
        $(".com-checkbox3").hide();
        $(".com-cleanUp").show();
        $(".com-list-main").removeClass("w-Listmargin");
        $(".common-cleanUp").show();
        $(".com-achieve").hide();
        $(".community-tab a").removeClass("mui-disabled");
        checkbox3.attr("checked", false);
        $(".com-operation3").slideUp("fast");

    });
});
var delete3 = $(".com-operation3 .com-delete");
delete3.click(function() {
    var checked = $(".com-checkbox3 input[name='checkbox']:checked");
    var len = checked.length;
    var btnArray = ['否', '是'];
    mui.confirm('确认删除选中'+len+'话题？', '删除后将无法恢复', btnArray, function(e) {
        if(e.index == 1) {
            thisLi = checked.parents('dd');
            mui.toast('删除成功！');
            thisLi.remove();
        } else {
            mui.toast('已取消！');
        }
    })
});




// 阅读页上下弹窗
$("#detail-img-content").click(function () {
    $(".detail-header").toggleClass("hidden");
    $(".detail-tool").toggleClass("hidden");
    $('.toTop').toggleClass('hidden');
});

// 送礼物弹窗
$("#sendgift").click(function () {
    $(".gift-bg").toggleClass("hidden");
    $(".send-gift").toggleClass("hidden");
    $(".huniang-mascot").toggleClass("hidden");
    $(".fans-rank-mascot").toggleClass("hidden");

    $("body").css("overflow","hidden")
});
$(".gift-close").click(function () {
    $(".gift-bg").toggleClass("hidden");
    $(".send-gift").toggleClass("hidden");
    $(".huniang-mascot").toggleClass("hidden");
    $(".fans-rank-mascot").toggleClass("hidden");
    $("body").css("overflow","auto")
});

//目录正序倒序
$(function() {
    $("#chapter-list-order").click(function() {
        var orderIdArray = [];
        var idIndex = [];
        var mode = $(this).attr("mode");
        var orderid = $(".orderid");

        orderid.each(function(i) {
            var id = parseInt($(this).data('chapter'));
            idIndex[id] = i;
            orderIdArray.push(id);
        });
        if(mode == 0)
        {
            $(this).attr("mode", 1);
            $(this).addClass("order-desc");
            $(".order-name").html("倒序");
            orderIdArray = orderIdArray.sort(function(a, b){return (a < b) ? 1 : -1});
        }
        else if(mode == 1)
        {
            $(this).attr("mode", 0);
            $(this).removeClass("order-desc");
            $(".order-name").html("正序");
            orderIdArray = orderIdArray.sort(function(a, b){return (a > b) ? 1 : -1});
        }
        var list = $("#sort-list").find("li");
        var _length = orderIdArray.length;
        for (var i=0; i<_length; i++)
        {
            $("#sort-list").append(list.eq(idIndex[orderIdArray[i]]));
        }
    });
});




//目录弹出
$("#chapterList").click(function () {
    $(".detail-chapter-list").removeClass("hidden");
    $(".detail-header").addClass("hidden");
    $(".detail-tool").addClass("hidden");
    $('.toTop').addClass("hidden");
    $("body").css("overflow","hidden")
});
$("#detail-img-content").click(function () {
    $(".detail-chapter-list").addClass("hidden");
    $("body").css("overflow","auto")
});

//设置弹出
$("#setting").click(function () {
    $(".detail-set").removeClass("hidden");
    $(".detail-header").addClass("hidden");
    $(".detail-tool").addClass("hidden");
    $("body").css("overflow","hidden")
});
$("#detail-img-content").click(function () {
    $(".detail-set").addClass("hidden");
    $("body").css("overflow","auto")
});



// 话题图片居中自适应
$(window).load(function () {
    var liW = $(".liwidth").width();
    var liH = $(".liwidth").height();
    $('.imgwidth').each(function(){
        var imgheight = $(this).height();
        var imgwidth = $(this).width();
        var remsize = imgwidth/imgheight;

        if(remsize>1){
            imgwidth=imgwidth*liH/imgheight;
            $(this).css({"height":liW+'px','margin-left':'-'+ Math.round((imgwidth-liW)/2)+'px'});
        }else{
            imgheight=imgheight*liW/imgwidth;
            $(this).css({"width":liH+'px','margin-top':'-'+ Math.round((imgheight-liH)/2)+'px'});
        }
    })
});

//展开评论
$('.cdc-open').click(function() {
    $('.cdc-reply-other-dd' + $(this).data('post-id')).slideToggle("fast");
    $('.cdc-open-1, .cdc-open-2').toggle();
});

//评论框点击
$('.default-text').click(function() {
    $('.community-footer-default').hide();
    $('.community-footer-focus').show();
    $('.focus-text').focus();
});

//判断输入框是否输入
function inputFun() {
    var inputi = $('.focus-text').html();
    if(inputi == '' || inputi == '<br>') {
        $('.community-footer-btn-submit').removeClass('community-footer-btn-inputing');
    } else {
        $('.community-footer-btn-submit').addClass('community-footer-btn-inputing');
    }
}
$('.focus-text').bind('input propertychange', function() {
    inputFun();
});

$('.host-content').click(function() {

})
function hostContent(o) {
    var beReplyer = $(o).attr("username");
    var pid = $(o).attr('pid');
    var baseid = $(o).attr('baseid');
    $('.community-footer-default').hide();
    $('.community-footer-focus').show();
    $('.comment-mod').show();

    $(".comment-footer").hide();

    $('.focus-text').html('');
    $('.focus-text').html("<div class=comment-notice>" +"回复："+beReplyer + "</div>");
    $('.focus-text').attr({pid:pid,baseid:baseid,post_user_pnickname : $(o).attr('post_user_pnickname')}).addClass('reply');
}



/*话题关注*/
$('.g-follow-add').click(function() {
    mui.toast("关注成功");
    $(this).removeClass('g-follow-add').html('已关注');
});


/*话题举报*/
$('.community-report-btn').click(function() {
    var btnArray = ['是', '否'];
    mui.confirm('确定举报？', '温馨提醒', btnArray, function(e) {
        if(e.index == 1) {

        } else {
            mui.toast("举报成功");
        }
    })
});

// 收藏漫画
function shelfCartoon(book_id, cls) {
    alert(book_id);
}


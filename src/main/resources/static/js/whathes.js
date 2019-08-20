//提交回复的js
function post() {
    var questionId =$("#question_id").val();
    var content =$("#comment_content").val();
    comment2target(questionId,1,content);

}

function comment2target(targetId,type,content) {
    if (!content){
        alert("回复不能为空哟！");
        return;
    }
    $.ajax({
        type:"post",
        url:"/comment",
        contentType:'application/json',
        data:
            JSON.stringify({
                "parentId":targetId,
                "content":content,
                "type":type
            }),
        success:function (response) {
            if(response.code==200){
                // $("#comment_section").hide();
                window.location.reload();
            }else{
                if (response.code==2003){
                    var isAcception=confirm(response.message);
                    if (isAcception){
                        window.open("https://github.com/login/oauth/authorize?client_id=0473f27e35a5a296e9b2&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closeble","true");
                    }
                }
                alert(response.message);
            }
            console.log(response)
        },
        dataType:"JSON"

    });

    console.log(targetId);
    console.log(content);
}

function comment(e) {
    var commentId =e.getAttribute("data-id");
    var content = $("#input-"+commentId).val();
    if (!content){
        alert("回复不能为空哟！");
        return;
    }
    $.ajax({
        type:"post",
        url:"/comment",
        contentType:'application/json',
        data:
            JSON.stringify({
                "parentId":commentId,
                "content":content,
                "type":2
            }),
        success:function (response) {
            if(response.code==200){
                window.location.reload();
            }else{
                if (response.code==2003){
                    var isAcception=confirm(response.message);
                    if (isAcception){
                        window.open("https://github.com/login/oauth/authorize?client_id=0473f27e35a5a296e9b2&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closeble","true");
                    }
                }
                alert(response.message);
            }
            console.log(response)
        },
        dataType:"JSON"

    })
}













//二级评论的js
function collapseComments(e) {
   var id =e.getAttribute("data-id");
   var comment = $("#comment-"+id);

  var collapse= e.getAttribute("data-collapse");
   if (collapse){
       //关闭2级
       comment.removeClass("in");
       e.removeAttribute("data-collapse");
       e.classList.remove("active");
   }else {
       var subcommentContainer = $("#comment-" + id);
       console.log(subcommentContainer);
       if (subcommentContainer.children().length != 2) {

           //展开 2 级评论
           comment.addClass("in");
           console.log(id);
           //标记2级评论状态
           e.setAttribute("data-collapse", "in");
           e.classList.add("active");

       } else {

           console.log("children=2");
           console.log(subcommentContainer.children().length);
           $.getJSON("/comment/" + id, function (data) {
               console.log(data);
               // var subcommentContainer=$("#comment-"+id)
               $.each(data.data.reverse(), function (index, comment) {

                   var avatarElement=$("<img/>",{
                       "class":"media-object img-rounded",
                       "src":comment.user.avatarUrl
                   });

                   var mediaLeftElement=$("<div/>",{
                       "class":"media-left",
                   });

                   var mediaBodyElement=$("<div/>",{
                       "class":"media-body",
                   }).append($("<h5/>",{
                       "class":"media-heading",
                       "html":comment.user.name
                   })).append($("<div/>",{
                       "html":comment.content
                   })).append($("<div/>",{
                       "class":"menu",
                   })).append($("<span/>",{
                       "class":"pull-right",
                       "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                   }));

                    mediaLeftElement.append(avatarElement);
                   var mediaElement =$("<div/>",{
                      "class":"media",
                   });
                    mediaElement.append(mediaLeftElement).append(mediaBodyElement);
                   var commentElement = $("<div/>", {
                       "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                   });
                   commentElement.append(mediaElement);
                   subcommentContainer.prepend(commentElement);

               });
               //展开 2 级评论
               comment.addClass("in");
               console.log(id);
               //标记2级评论状态
               e.setAttribute("data-collapse", "in");
               e.classList.add("active");
           });

       }
   }
}

function selectTagByTag(e) {
    var value =e.getAttribute("data-tag");
    var previous =$("#tag").val();

    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

function showSelectTag() {
    $("#tagShow").show();
}

function likeCount(e) {
    var id =e.getAttribute("data-id");
    var comment = $("#like-"+id);
    var flag =false;
    console.log(comment.children().length);
    var showing = "<span id="+id+"></span>";

    if (comment.children().length == 2) {
        //展开 2 级评论
        e.classList.add("like-count");
        $(e).append(showing);

        // $.ajax({
        //     type:"get",
        //     url:"/likeCountUp/"+id,
        //     success:function () {
        //
        //     },
        //
        //
        // })

        //标记2级评论状态
        // if (!flag){
        //     // e.setAttribute("data-collapse", "in");
        //     e.classList.add("like-count");
        // }


    }else {
        e.classList.remove("like-count");
        $('#'+id).remove();

    }

}



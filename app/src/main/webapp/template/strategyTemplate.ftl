
<div class="container">
    <h6>当季推荐</h6>
    <div class="row hot">
        <#list hot as h>
            <div class="col ">
                <a href="../strategyCatalogs.html?id=${h.id}">
                    <img src="${h.coverUrl}">
                    <p>0次浏览</p>
                </a>
            </div>
        </#list>
    </div>
</div>
<hr>
<div class="container">
    <h6>全部攻略</h6>
    <div class="row classify ">
        <#list all as a>
            <div class="col-6 mb">
                <a href="../strategyCatalogs.html?id=${a.id}">
                    <img class="float-left " src="${a.coverUrl}">
                    <div class="classify-text">
                        <span>${a.title}</span>
                        <p>0人收藏</p>
                    </div>
                </a>
            </div>
        </#list>
    </div>
</div>

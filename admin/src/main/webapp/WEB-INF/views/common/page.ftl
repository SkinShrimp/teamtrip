<div style="text-align: center;">
    <ul id="pagination" class="pagination"></ul>
</div>
<script>
//分页
$(function(){
    $('#pagination').twbsPagination({ // 根据 JS 对象的属性值动态生成分组组件
        first: '首页',
        prev: '上一页',
        next: '下一页',
        last: '尾页',
        totalPages: ${pageInfo.pages}||1, // 总页数
        visiblePages: 5, // 可见页码
        startPage: ${pageInfo.pageNum}||1,// 当前页
        onPageClick: function (event, page) { // 翻页操作，都会执行这个函数 到底要翻到第几页，赋值 page
            $('#currentPage').val(page);
            $('#searchForm').submit();
        }
    });
})
</script>
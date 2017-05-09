<#macro categ catname>
<div class="panel-body">
    <ul class="nav nav-pills nav-stacked category-menu">
    <#if categories??>
        <#list categories as cat>
            <li id="${cat.id}">
                <a href="/catalog/${cat.id}">${cat.name}<span class="badge pull-right">42</span></a>
                <ul>
                    <#list cat.tags as t>
                        <li><a href="#">${t}</a>
                        </li>
                    </#list>
                </ul>
            </li>
        </#list>
    </#if>
    </ul>
</div>
<script>
    $("#${catname}").addClass("active");
</script>
</#macro>
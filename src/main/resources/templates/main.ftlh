<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить новый наряд
    </a>

<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type ="text" class="form-control" name="taskDiscription" placeholder="Введите описание задания"/>
        </div>
        <div class="form-group">
            <input type ="text" class="form-control" name="taskPlace" placeholder="Введите место задания"/>
        </div>
        <div class="form-group">
            <input type ="text" class="form-control"  name="taskTag" placeholder="Введите тэг"/>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file" id="customFile">
                <label class="custom-file-label" for="customFile">Выберите сайт</label>
            </div>
        </div>
        <button type="submit">Добавить</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
    </div>
</div>

<div class="card-columns">
     <#list tasks as task>
    <div class="card my-3">
        <#if task.filename??>
            <img src="/img/${task.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${task.taskDiscription}<p></span>
            <span>${task.taskPlace}<p></span>
            <i>${task.taskTag}<p></i>
        </div>
        <div class="card-footer text-muted">
            ${task.authorName}
        </div>
    </div>
    <#else>
    Нарядов не найдено!
    </#list>
</div>
</@c.page>
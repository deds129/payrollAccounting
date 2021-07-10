<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
</div>

<div>
    <form method="post">
        <input type="hidden" name="_csrf" value="{{_csrf.token}}">
        <input type ="text" name="taskDiscription" placeholder="Введите описание задания"/>
        <input type ="text" name="taskPlace" placeholder="Введите место задания"/>
        <input type ="text" name="taskTag" placeholder="Введите тэг"/>
        <button type="submit">Добавить</button>
    </form>
</div>

<div>Task list</div>
<form method="post" action="filter">
    <input type="hidden" name="_csrf" value="{{_csrf.token}}">
    <input type="text" name = "filter">
    <button type="submit">Найти</button>
</form>
<#list tasks as task>
<div>
    <b>${task.taskId}</b>
    <b>${task.taskDiscription}</b>
    <span>${task.taskPlace}</span>
    <i>${task.taskTag}</i>
    <strong>${task.authorName}</strong>
</div>
<#else>
    Tasks not found!
</#list>
</@c.page>
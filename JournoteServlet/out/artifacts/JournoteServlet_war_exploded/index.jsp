<%--
  Created by IntelliJ IDEA.
  User: Marz
  Date: 2019-12-09
  Time: 00:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Journote-Back-UI</title>
  </head>
  <body>
  journal_query
  <form action="/JournoteServlet/journalqueryservlet.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="submit" value="query">
  </form>
  <hr>
  journal_query_bynoti
  <form action="/JournoteServlet/journalquerybynotiservlet.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="submit" value="query">
  </form>
  <hr>
  journal_query_bylabel
  <form action="/JournoteServlet/journalquerybylabelservlet.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="text" name="label" placeholder="label">
    <input type="submit" value="query">
  </form>
  <hr>
  note_query
  <form action="/JournoteServlet/notequeryservlet.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="submit" value="query">
  </form>
  <hr>
  note_query_bynoti
  <form action="/JournoteServlet/notequerybynotiservlet.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="submit" value="query">
  </form>
  <hr>
  note_query_bylabel
  <form action="/JournoteServlet/notequerybylabelservlet.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="text" name="label" placeholder="label">
    <input type="submit" value="query">
  </form>
  <hr>
  register
  <form action="/JournoteServlet/journoteregister.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="text" name="password" placeholder="password">
    <input type="submit" value="login">
  </form>
  <hr>
  login
  <form action="/JournoteServlet/journotelogin.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="text" name="password" placeholder="password">
    <input type="submit" value="login">
  </form>
  <hr>
  insert_journal
  <form action="/JournoteServlet/journalinsertservlet.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="text" name="title" placeholder="title">
    <input type="text" name="content" placeholder="content">
    <input type="submit" value="insert">
  </form>
  <hr>
  edit_journal
  <form action="/JournoteServlet/journaleditservlet.do" method="post">
    <input type="text" name="tag" placeholder="tag">
    <input type="text" name="title" placeholder="title">
    <input type="text" name="content" placeholder="content">
    <input type="submit" value="edit">
  </form>
  <hr>
  delete_journote
  <form action="/JournoteServlet/journotedeleteservlet.do" method="post">
    <input type="text" name="tag" placeholder="tag">
    <input type="submit" value="delete">
  </form>
  <hr>
  insert_note
  <form action="/JournoteServlet/noteinsertservlet.do" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="text" name="title" placeholder="title">
    <input type="text" name="content" placeholder="content">
    <input type="submit" value="insert">
  </form>
  <hr>
  edit_note
  <form action="/JournoteServlet/noteeditservlet.do" method="post">
    <input type="text" name="tag" placeholder="tag">
    <input type="text" name="title" placeholder="title">
    <input type="text" name="content" placeholder="content">
    <input type="submit" value="edit">
  </form>
  <hr>
  insert_notification
  <form action="/JournoteServlet/notificationinsertservlet.do" method="post">
    <input type="text" name="journoteTag" placeholder="journoteTag">
    <input type="text" name="date_str" placeholder="date_str">
    <input type="submit" value="insert">
  </form>
  <hr>
  delete_notification
  <form action="/JournoteServlet/notificationdeleteservlet.do" method="post">
    <input type="text" name="journoteTag" placeholder="journoteTag">
    <input type="submit" value="delete">
  </form>
  <hr>
  update_notification
  <form action="/JournoteServlet/notificationupdateservlet.do" method="post">
    <input type="text" name="journoteTag" placeholder="journoteTag">
    <input type="text" name="date_str" placeholder="date_str">
    <input type="submit" value="update">
  </form>
  <hr>
  query_notification
  <form action="/JournoteServlet/notificationqueryservlet.do" method="post">
    <input type="text" name="journoteTag" placeholder="journoteTag">
    <input type="submit" value="query">
  </form>
  <hr>
  label_update
  <form action="/JournoteServlet/labelupdateservlet.do" method="post">
    <input type="text" name="journoteTag" placeholder="journoteTag">
    <input type="text" name="label" placeholder="label">
    <input type="submit" value="update">
  </form>
  <hr>
  journote_query_hasnoti
  <form action="/JournoteServlet/isjournotehasnotiservlet.do" method="post">
    <input type="text" name="journoteTag" placeholder="journoteTag">
    <input type="submit" value="query">
  </form>
  <hr>
  </body>
</html>


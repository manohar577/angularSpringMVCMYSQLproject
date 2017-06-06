<?php
 
session_start();
 
/* get list of authorized user from a wiki page */
$authfilename = dirname(realpath($_SERVER['SCRIPT_FILENAME']))
  ."/users";
$fd = fopen($authfilename, "r");
$authfile = fread($fd, filesize($authfilename));
fclose($fd);
 
$authorized_users = "";
preg_match_all("/\"\s*\*\s*([a-zA-Z0-9]+)/", $authfile, $users);
for ($i=0; $i<count($users[0]); $i++) {
  $authorized_users .= " ". $users[1][$i];
}
 
DoCASAuth($authorized_users);
 
function DoCASAuth($authorized_users) {
if(!isset($_SESSION['user'])) {
  /* get current page */
  $mepage = 'http';
  if($_SERVER['HTTPS']=='on') { $mepage .= 's'; }
  $mepage .= '://'. $_SERVER['SERVER_NAME'] . $_SERVER['SCRIPT_NAME'];
  if($_SERVER['QUERY_STRING']>' ') { $mepage .= '?'. $_SERVER['QUERY_STRING'];}
 
  $cas = "http://cas.nau.edu/cas/";
 
  if(!isset($_GET['ticket'])) {
    /* redirect to cas */
    $_SESSION['cas_service'] = $mepage;
    header("Location: ". $cas ."index.jsp?service=". $mepage);
    exit();
  } else {
    /* have ticket - check it */
    $fd = fopen($cas ."serviceValidate?ticket="
      .$_GET['ticket'] ."&service=". $_SESSION['cas_service'],"r");
    $page = '';
    while (!feof($fd)) {
      $page .= fread($fd, 8192);
    }
    fclose($fd);
    if(preg_match("/<cas:user>(.*)<\/cas:user>/", $page, $matches)) {
      /* good ticket - store in session */
      $_SESSION['user'] = $matches[1];
    } else {
      /* bad ticket - try again */
      $_SESSION['cas_service'] = $mepage;
      header("Location: ". $cas ."index.jsp?service=". $mepage);
      exit();
    }
    echo "Checked ticket.<br>";
    unset($_SESSION['cas_service']);
  }
} else {
  /* check if user is allowed here */
  $user = $_SESSION['user'];
  if(stristr(" ". $authorized_users ." ", " ".$user." ")===false) {
    unset($_SESSION['user']);
    echo "You [". $user ."] are not authorized to see this stuff, sorry!";
    exit();
  }
}
}
?>
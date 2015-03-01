




<!DOCTYPE html>
<html>
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# object: http://ogp.me/ns/object# article: http://ogp.me/ns/article# profile: http://ogp.me/ns/profile#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>form2js/src/form2js.js at master · maxatwork/form2js</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />
    <link rel="apple-touch-icon" sizes="57x57" href="/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-144.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144.png" />
    <meta property="fb:app_id" content="1401488693436528"/>

      <meta content="@github" name="twitter:site" /><meta content="summary" name="twitter:card" /><meta content="maxatwork/form2js" name="twitter:title" /><meta content="form2js - Javascript library for collecting form data" name="twitter:description" /><meta content="https://2.gravatar.com/avatar/476f2e06733dd383aaa5a9f06660fc69?d=https%3A%2F%2Fidenticons.github.com%2F82264f90b08ce99ca0ef00a6051d3170.png&amp;r=x&amp;s=400" name="twitter:image:src" />
<meta content="GitHub" property="og:site_name" /><meta content="object" property="og:type" /><meta content="https://2.gravatar.com/avatar/476f2e06733dd383aaa5a9f06660fc69?d=https%3A%2F%2Fidenticons.github.com%2F82264f90b08ce99ca0ef00a6051d3170.png&amp;r=x&amp;s=400" property="og:image" /><meta content="maxatwork/form2js" property="og:title" /><meta content="https://github.com/maxatwork/form2js" property="og:url" /><meta content="form2js - Javascript library for collecting form data" property="og:description" />

    <meta name="hostname" content="github-fe119-cp1-prd.iad.github.net">
    <meta name="ruby" content="ruby 2.1.0p0-github-tcmalloc (87d8860372) [x86_64-linux]">
    <link rel="assets" href="https://github.global.ssl.fastly.net/">
    <link rel="conduit-xhr" href="https://ghconduit.com:25035/">
    <link rel="xhr-socket" href="/_sockets" />
    


    <meta name="msapplication-TileImage" content="/windows-tile.png" />
    <meta name="msapplication-TileColor" content="#ffffff" />
    <meta name="selected-link" value="repo_source" data-pjax-transient />
    <meta content="collector.githubapp.com" name="octolytics-host" /><meta content="collector-cdn.github.com" name="octolytics-script-host" /><meta content="github" name="octolytics-app-id" /><meta content="7784445E:29F0:429F796:52ED194C" name="octolytics-dimension-request_id" /><meta content="515574" name="octolytics-actor-id" /><meta content="rayliu" name="octolytics-actor-login" /><meta content="cafb2e9ae9a420bc12971f7aeaa0ab380fca14eb30b01b78d6cdbec922a21a4b" name="octolytics-actor-hash" />
    

    
    
    <link rel="icon" type="image/x-icon" href="/favicon.ico" />

    <meta content="authenticity_token" name="csrf-param" />
<meta content="VoGH3J42+VtwCwIEtZ5751Bla0oZJ4/Pu1Sb0nCJ7Vw=" name="csrf-token" />

    <link href="https://github.global.ssl.fastly.net/assets/github-ff4edf09390055458cebc46ef922e61ee1f5fc52.css" media="all" rel="stylesheet" type="text/css" />
    <link href="https://github.global.ssl.fastly.net/assets/github2-09d4cddbd997c7aff84e5b1a25917774ca5c8fd7.css" media="all" rel="stylesheet" type="text/css" />
    


      <script src="https://github.global.ssl.fastly.net/assets/frameworks-02ee5d6f13ffacca6207d163a3c10aba4fb80b16.js" type="text/javascript"></script>
      <script async="async" defer="defer" src="https://github.global.ssl.fastly.net/assets/github-ddf95c725e6956db29688761f8bd21b3a2961168.js" type="text/javascript"></script>
      
      <meta http-equiv="x-pjax-version" content="91108283203ecd1f2ae6589c4e82de7c">

        <link data-pjax-transient rel='permalink' href='/maxatwork/form2js/blob/e2977192084849abce80d2c9884403cc01ebb387/src/form2js.js'>

  <meta name="description" content="form2js - Javascript library for collecting form data" />

  <meta content="397263" name="octolytics-dimension-user_id" /><meta content="maxatwork" name="octolytics-dimension-user_login" /><meta content="906794" name="octolytics-dimension-repository_id" /><meta content="maxatwork/form2js" name="octolytics-dimension-repository_nwo" /><meta content="true" name="octolytics-dimension-repository_public" /><meta content="false" name="octolytics-dimension-repository_is_fork" /><meta content="906794" name="octolytics-dimension-repository_network_root_id" /><meta content="maxatwork/form2js" name="octolytics-dimension-repository_network_root_nwo" />
  <link href="https://github.com/maxatwork/form2js/commits/master.atom" rel="alternate" title="Recent Commits to form2js:master" type="application/atom+xml" />

  </head>


  <body class="logged_in  env-production macintosh vis-public page-blob">
    <div class="wrapper">
      
      
      
      


      <div class="header header-logged-in true">
  <div class="container clearfix">

    <a class="header-logo-invertocat" href="https://github.com/">
  <span class="mega-octicon octicon-mark-github"></span>
</a>

    
    <a href="/notifications" class="notification-indicator tooltipped downwards" data-gotokey="n" title="You have unread notifications">
        <span class="mail-status unread"></span>
</a>

      <div class="command-bar js-command-bar  in-repository">
          <form accept-charset="UTF-8" action="/search" class="command-bar-form" id="top_search_form" method="get">

<input type="text" data-hotkey="/ s" name="q" id="js-command-bar-field" placeholder="Search or type a command" tabindex="1" autocapitalize="off"
    
    data-username="rayliu"
      data-repo="maxatwork/form2js"
      data-branch="master"
      data-sha="46a4925e342c82b8a9c6605eaefc945e65d54352"
  >

    <input type="hidden" name="nwo" value="maxatwork/form2js" />

    <div class="select-menu js-menu-container js-select-menu search-context-select-menu">
      <span class="minibutton select-menu-button js-menu-target">
        <span class="js-select-button">This repository</span>
      </span>

      <div class="select-menu-modal-holder js-menu-content js-navigation-container">
        <div class="select-menu-modal">

          <div class="select-menu-item js-navigation-item js-this-repository-navigation-item selected">
            <span class="select-menu-item-icon octicon octicon-check"></span>
            <input type="radio" class="js-search-this-repository" name="search_target" value="repository" checked="checked" />
            <div class="select-menu-item-text js-select-button-text">This repository</div>
          </div> <!-- /.select-menu-item -->

          <div class="select-menu-item js-navigation-item js-all-repositories-navigation-item">
            <span class="select-menu-item-icon octicon octicon-check"></span>
            <input type="radio" name="search_target" value="global" />
            <div class="select-menu-item-text js-select-button-text">All repositories</div>
          </div> <!-- /.select-menu-item -->

        </div>
      </div>
    </div>

  <span class="octicon help tooltipped downwards" title="Show command bar help">
    <span class="octicon octicon-question"></span>
  </span>


  <input type="hidden" name="ref" value="cmdform">

</form>
        <ul class="top-nav">
          <li class="explore"><a href="/explore">Explore</a></li>
            <li><a href="https://gist.github.com">Gist</a></li>
            <li><a href="/blog">Blog</a></li>
          <li><a href="https://help.github.com">Help</a></li>
        </ul>
      </div>

    


  <ul id="user-links">
    <li>
      <a href="/rayliu" class="name">
        <img alt="liuyubie" height="20" src="https://0.gravatar.com/avatar/d83ab667b8e6f0619be97572f5336c91?d=https%3A%2F%2Fidenticons.github.com%2F2d8eb8e6dee60c1e5e4caef0a1147d9e.png&amp;r=x&amp;s=140" width="20" /> rayliu
      </a>
    </li>

    <li class="new-menu dropdown-toggle js-menu-container">
      <a href="#" class="js-menu-target tooltipped downwards" title="Create new..." aria-label="Create new...">
        <span class="octicon octicon-plus"></span>
        <span class="dropdown-arrow"></span>
      </a>

      <div class="js-menu-content">
      </div>
    </li>

    <li>
      <a href="/settings/profile" id="account_settings"
        class="tooltipped downwards"
        aria-label="Account settings "
        title="Account settings ">
        <span class="octicon octicon-tools"></span>
      </a>
    </li>
    <li>
      <a class="tooltipped downwards" href="/logout" data-method="post" id="logout" title="Sign out" aria-label="Sign out">
        <span class="octicon octicon-log-out"></span>
      </a>
    </li>

  </ul>

<div class="js-new-dropdown-contents hidden">
  

<ul class="dropdown-menu">
  <li>
    <a href="/new"><span class="octicon octicon-repo-create"></span> New repository</a>
  </li>
  <li>
    <a href="/organizations/new"><span class="octicon octicon-organization"></span> New organization</a>
  </li>


    <li class="section-title">
      <span title="maxatwork/form2js">This repository</span>
    </li>
      <li>
        <a href="/maxatwork/form2js/issues/new"><span class="octicon octicon-issue-opened"></span> New issue</a>
      </li>
</ul>

</div>


    
  </div>
</div>

      

      




          <div class="site" itemscope itemtype="http://schema.org/WebPage">
    
    <div class="pagehead repohead instapaper_ignore readability-menu">
      <div class="container">
        

<ul class="pagehead-actions">

    <li class="subscription">
      <form accept-charset="UTF-8" action="/notifications/subscribe" class="js-social-container" data-autosubmit="true" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="authenticity_token" type="hidden" value="VoGH3J42+VtwCwIEtZ5751Bla0oZJ4/Pu1Sb0nCJ7Vw=" /></div>  <input id="repository_id" name="repository_id" type="hidden" value="906794" />

    <div class="select-menu js-menu-container js-select-menu">
      <a class="social-count js-social-count" href="/maxatwork/form2js/watchers">
        34
      </a>
      <span class="minibutton select-menu-button with-count js-menu-target" role="button" tabindex="0">
        <span class="js-select-button">
          <span class="octicon octicon-eye-watch"></span>
          Watch
        </span>
      </span>

      <div class="select-menu-modal-holder">
        <div class="select-menu-modal subscription-menu-modal js-menu-content">
          <div class="select-menu-header">
            <span class="select-menu-title">Notification status</span>
            <span class="octicon octicon-remove-close js-menu-close"></span>
          </div> <!-- /.select-menu-header -->

          <div class="select-menu-list js-navigation-container" role="menu">

            <div class="select-menu-item js-navigation-item selected" role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input checked="checked" id="do_included" name="do" type="radio" value="included" />
                <h4>Not watching</h4>
                <span class="description">You only receive notifications for conversations in which you participate or are @mentioned.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-eye-watch"></span>
                  Watch
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

            <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input id="do_subscribed" name="do" type="radio" value="subscribed" />
                <h4>Watching</h4>
                <span class="description">You receive notifications for all conversations in this repository.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-eye-unwatch"></span>
                  Unwatch
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

            <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input id="do_ignore" name="do" type="radio" value="ignore" />
                <h4>Ignoring</h4>
                <span class="description">You do not receive any notifications for conversations in this repository.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-mute"></span>
                  Stop ignoring
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

          </div> <!-- /.select-menu-list -->

        </div> <!-- /.select-menu-modal -->
      </div> <!-- /.select-menu-modal-holder -->
    </div> <!-- /.select-menu -->

</form>
    </li>

  <li>
  

  <div class="js-toggler-container js-social-container starring-container ">
    <a href="/maxatwork/form2js/unstar"
      class="minibutton with-count js-toggler-target star-button starred upwards"
      title="Unstar this repository" data-remote="true" data-method="post" rel="nofollow">
      <span class="octicon octicon-star-delete"></span><span class="text">Unstar</span>
    </a>

    <a href="/maxatwork/form2js/star"
      class="minibutton with-count js-toggler-target star-button unstarred upwards"
      title="Star this repository" data-remote="true" data-method="post" rel="nofollow">
      <span class="octicon octicon-star"></span><span class="text">Star</span>
    </a>

      <a class="social-count js-social-count" href="/maxatwork/form2js/stargazers">
        332
      </a>
  </div>

  </li>


        <li>
          <a href="/maxatwork/form2js/fork" class="minibutton with-count js-toggler-target fork-button lighter upwards" title="Fork this repo" rel="nofollow" data-method="post">
            <span class="octicon octicon-git-branch-create"></span><span class="text">Fork</span>
          </a>
          <a href="/maxatwork/form2js/network" class="social-count">84</a>
        </li>


</ul>

        <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public">
          <span class="repo-label"><span>public</span></span>
          <span class="mega-octicon octicon-repo"></span>
          <span class="author">
            <a href="/maxatwork" class="url fn" itemprop="url" rel="author"><span itemprop="title">maxatwork</span></a>
          </span>
          <span class="repohead-name-divider">/</span>
          <strong><a href="/maxatwork/form2js" class="js-current-repository js-repo-home-link">form2js</a></strong>

          <span class="page-context-loader">
            <img alt="Octocat-spinner-32" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
          </span>

        </h1>
      </div><!-- /.container -->
    </div><!-- /.repohead -->

    <div class="container">
      

      <div class="repository-with-sidebar repo-container new-discussion-timeline js-new-discussion-timeline  ">
        <div class="repository-sidebar">
            

<div class="sunken-menu vertical-right repo-nav js-repo-nav js-repository-container-pjax js-octicon-loaders">
  <div class="sunken-menu-contents">
    <ul class="sunken-menu-group">
      <li class="tooltipped leftwards" title="Code">
        <a href="/maxatwork/form2js" aria-label="Code" class="selected js-selected-navigation-item sunken-menu-item" data-gotokey="c" data-pjax="true" data-selected-links="repo_source repo_downloads repo_commits repo_tags repo_branches /maxatwork/form2js">
          <span class="octicon octicon-code"></span> <span class="full-word">Code</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

        <li class="tooltipped leftwards" title="Issues">
          <a href="/maxatwork/form2js/issues" aria-label="Issues" class="js-selected-navigation-item sunken-menu-item js-disable-pjax" data-gotokey="i" data-selected-links="repo_issues /maxatwork/form2js/issues">
            <span class="octicon octicon-issue-opened"></span> <span class="full-word">Issues</span>
            <span class='counter'>40</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>        </li>

      <li class="tooltipped leftwards" title="Pull Requests">
        <a href="/maxatwork/form2js/pulls" aria-label="Pull Requests" class="js-selected-navigation-item sunken-menu-item js-disable-pjax" data-gotokey="p" data-selected-links="repo_pulls /maxatwork/form2js/pulls">
            <span class="octicon octicon-git-pull-request"></span> <span class="full-word">Pull Requests</span>
            <span class='counter'>11</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>


        <li class="tooltipped leftwards" title="Wiki">
          <a href="/maxatwork/form2js/wiki" aria-label="Wiki" class="js-selected-navigation-item sunken-menu-item" data-pjax="true" data-selected-links="repo_wiki /maxatwork/form2js/wiki">
            <span class="octicon octicon-book"></span> <span class="full-word">Wiki</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>        </li>
    </ul>
    <div class="sunken-menu-separator"></div>
    <ul class="sunken-menu-group">

      <li class="tooltipped leftwards" title="Pulse">
        <a href="/maxatwork/form2js/pulse" aria-label="Pulse" class="js-selected-navigation-item sunken-menu-item" data-pjax="true" data-selected-links="pulse /maxatwork/form2js/pulse">
          <span class="octicon octicon-pulse"></span> <span class="full-word">Pulse</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

      <li class="tooltipped leftwards" title="Graphs">
        <a href="/maxatwork/form2js/graphs" aria-label="Graphs" class="js-selected-navigation-item sunken-menu-item" data-pjax="true" data-selected-links="repo_graphs repo_contributors /maxatwork/form2js/graphs">
          <span class="octicon octicon-graph"></span> <span class="full-word">Graphs</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

      <li class="tooltipped leftwards" title="Network">
        <a href="/maxatwork/form2js/network" aria-label="Network" class="js-selected-navigation-item sunken-menu-item js-disable-pjax" data-selected-links="repo_network /maxatwork/form2js/network">
          <span class="octicon octicon-git-branch"></span> <span class="full-word">Network</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>
    </ul>


  </div>
</div>

              <div class="only-with-full-nav">
                

  

<div class="clone-url open"
  data-protocol-type="http"
  data-url="/users/set_protocol?protocol_selector=http&amp;protocol_type=clone">
  <h3><strong>HTTPS</strong> clone URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="https://github.com/maxatwork/form2js.git" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="https://github.com/maxatwork/form2js.git" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>

  

<div class="clone-url "
  data-protocol-type="ssh"
  data-url="/users/set_protocol?protocol_selector=ssh&amp;protocol_type=clone">
  <h3><strong>SSH</strong> clone URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="git@github.com:maxatwork/form2js.git" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="git@github.com:maxatwork/form2js.git" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>

  

<div class="clone-url "
  data-protocol-type="subversion"
  data-url="/users/set_protocol?protocol_selector=subversion&amp;protocol_type=clone">
  <h3><strong>Subversion</strong> checkout URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="https://github.com/maxatwork/form2js" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="https://github.com/maxatwork/form2js" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>


<p class="clone-options">You can clone with
      <a href="#" class="js-clone-selector" data-protocol="http">HTTPS</a>,
      <a href="#" class="js-clone-selector" data-protocol="ssh">SSH</a>,
      or <a href="#" class="js-clone-selector" data-protocol="subversion">Subversion</a>.
  <span class="octicon help tooltipped upwards" title="Get help on which URL is right for you.">
    <a href="https://help.github.com/articles/which-remote-url-should-i-use">
    <span class="octicon octicon-question"></span>
    </a>
  </span>
</p>

  <a href="http://mac.github.com" data-url="github-mac://openRepo/https://github.com/maxatwork/form2js" class="minibutton sidebar-button js-conduit-rewrite-url">
    <span class="octicon octicon-device-desktop"></span>
    Clone in Desktop
  </a>


                <a href="/maxatwork/form2js/archive/master.zip"
                   class="minibutton sidebar-button"
                   title="Download this repository as a zip file"
                   rel="nofollow">
                  <span class="octicon octicon-cloud-download"></span>
                  Download ZIP
                </a>
              </div>
        </div><!-- /.repository-sidebar -->

        <div id="js-repo-pjax-container" class="repository-content context-loader-container" data-pjax-container>
          


<!-- blob contrib key: blob_contributors:v21:1f797dabc608a171a655eb70fd0a2cc3 -->

<p title="This is a placeholder element" class="js-history-link-replace hidden"></p>

<a href="/maxatwork/form2js/find/master" data-pjax data-hotkey="t" class="js-show-file-finder" style="display:none">Show File Finder</a>

<div class="file-navigation">
  

<div class="select-menu js-menu-container js-select-menu" >
  <span class="minibutton select-menu-button js-menu-target" data-hotkey="w"
    data-master-branch="master"
    data-ref="master"
    role="button" aria-label="Switch branches or tags" tabindex="0">
    <span class="octicon octicon-git-branch"></span>
    <i>branch:</i>
    <span class="js-select-button">master</span>
  </span>

  <div class="select-menu-modal-holder js-menu-content js-navigation-container" data-pjax>

    <div class="select-menu-modal">
      <div class="select-menu-header">
        <span class="select-menu-title">Switch branches/tags</span>
        <span class="octicon octicon-remove-close js-menu-close"></span>
      </div> <!-- /.select-menu-header -->

      <div class="select-menu-filters">
        <div class="select-menu-text-filter">
          <input type="text" aria-label="Filter branches/tags" id="context-commitish-filter-field" class="js-filterable-field js-navigation-enable" placeholder="Filter branches/tags">
        </div>
        <div class="select-menu-tabs">
          <ul>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="branches" class="js-select-menu-tab">Branches</a>
            </li>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="tags" class="js-select-menu-tab">Tags</a>
            </li>
          </ul>
        </div><!-- /.select-menu-tabs -->
      </div><!-- /.select-menu-filters -->

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="branches">

        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/maxatwork/form2js/blob/develop/src/form2js.js"
                 data-name="develop"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="develop">develop</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/maxatwork/form2js/blob/gh-pages/src/form2js.js"
                 data-name="gh-pages"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="gh-pages">gh-pages</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item selected">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/maxatwork/form2js/blob/master/src/form2js.js"
                 data-name="master"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="master">master</a>
            </div> <!-- /.select-menu-item -->
        </div>

          <div class="select-menu-no-results">Nothing to show</div>
      </div> <!-- /.select-menu-list -->

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="tags">
        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/maxatwork/form2js/tree/v2.0/src/form2js.js"
                 data-name="v2.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="v2.0">v2.0</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/maxatwork/form2js/tree/v1.0/src/form2js.js"
                 data-name="v1.0"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="v1.0">v1.0</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/maxatwork/form2js/tree/v0.3/src/form2js.js"
                 data-name="v0.3"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="v0.3">v0.3</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/maxatwork/form2js/tree/v0.2/src/form2js.js"
                 data-name="v0.2"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="v0.2">v0.2</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/maxatwork/form2js/tree/v0.1/src/form2js.js"
                 data-name="v0.1"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="v0.1">v0.1</a>
            </div> <!-- /.select-menu-item -->
        </div>

        <div class="select-menu-no-results">Nothing to show</div>
      </div> <!-- /.select-menu-list -->

    </div> <!-- /.select-menu-modal -->
  </div> <!-- /.select-menu-modal-holder -->
</div> <!-- /.select-menu -->

  <div class="breadcrumb">
    <span class='repo-root js-repo-root'><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/maxatwork/form2js" data-branch="master" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">form2js</span></a></span></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/maxatwork/form2js/tree/master/src" data-branch="master" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">src</span></a></span><span class="separator"> / </span><strong class="final-path">form2js.js</strong> <span class="js-zeroclipboard minibutton zeroclipboard-button" data-clipboard-text="src/form2js.js" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>


  <div class="commit file-history-tease">
    <img class="main-avatar" height="24" src="https://1.gravatar.com/avatar/fd3eb9bc4f137e48b1ed29a977d3c666?d=https%3A%2F%2Fidenticons.github.com%2Fb864650b74c76e359213ec9b1af77afe.png&amp;r=x&amp;s=140" width="24" />
    <span class="author"><a href="/kaiju" rel="author">kaiju</a></span>
    <time class="js-relative-date" datetime="2014-01-24T12:47:02-08:00" title="2014-01-24 12:47:02">January 24, 2014</time>
    <div class="commit-title">
        <a href="/maxatwork/form2js/commit/cc83b68a75b60ef0d1b952d298414e7b24172dbe" class="message" data-pjax="true" title="Don&#39;t return disabled fields regardless of skipEmpty behavior

Since browsers typically do not send disabled form elements on
form submit, it&#39;s probably improper behavior to include them when
constructing a structured object.

Added a check for a node&#39;s disabled property that returns an empty
array, thus preventing it from being added to formValues.">Don't return disabled fields regardless of skipEmpty behavior</a>
    </div>

    <div class="participation">
      <p class="quickstat"><a href="#blob_contributors_box" rel="facebox"><strong>5</strong> contributors</a></p>
          <a class="avatar tooltipped downwards" title="maxatwork" href="/maxatwork/form2js/commits/master/src/form2js.js?author=maxatwork"><img height="20" src="https://1.gravatar.com/avatar/476f2e06733dd383aaa5a9f06660fc69?d=https%3A%2F%2Fidenticons.github.com%2F82264f90b08ce99ca0ef00a6051d3170.png&amp;r=x&amp;s=140" width="20" /></a>
    <a class="avatar tooltipped downwards" title="kaiju" href="/maxatwork/form2js/commits/master/src/form2js.js?author=kaiju"><img height="20" src="https://1.gravatar.com/avatar/fd3eb9bc4f137e48b1ed29a977d3c666?d=https%3A%2F%2Fidenticons.github.com%2Fb864650b74c76e359213ec9b1af77afe.png&amp;r=x&amp;s=140" width="20" /></a>
    <a class="avatar tooltipped downwards" title="mcasimir" href="/maxatwork/form2js/commits/master/src/form2js.js?author=mcasimir"><img height="20" src="https://2.gravatar.com/avatar/98911c9d34157bbb0cf3dee5eddc6154?d=https%3A%2F%2Fidenticons.github.com%2F5e27aadc85f05f4119b07395a2194b33.png&amp;r=x&amp;s=140" width="20" /></a>
    <a class="avatar tooltipped downwards" title="DashausSP" href="/maxatwork/form2js/commits/master/src/form2js.js?author=DashausSP"><img height="20" src="https://1.gravatar.com/avatar/7a6bf9e4c61f55f42e7717034014de1b?d=https%3A%2F%2Fidenticons.github.com%2F9518a28a408b58d2bfe0d121b7d4d687.png&amp;r=x&amp;s=140" width="20" /></a>
    <a class="avatar tooltipped downwards" title="alejandroiglesias" href="/maxatwork/form2js/commits/master/src/form2js.js?author=alejandroiglesias"><img height="20" src="https://2.gravatar.com/avatar/4c97bbecf43064dc33208d838d30fe24?d=https%3A%2F%2Fidenticons.github.com%2F7cd0c47cadafb3c3a3670a1981c49a3b.png&amp;r=x&amp;s=140" width="20" /></a>


    </div>
    <div id="blob_contributors_box" style="display:none">
      <h2 class="facebox-header">Users who have contributed to this file</h2>
      <ul class="facebox-user-list">
          <li class="facebox-user-list-item">
            <img height="24" src="https://1.gravatar.com/avatar/476f2e06733dd383aaa5a9f06660fc69?d=https%3A%2F%2Fidenticons.github.com%2F82264f90b08ce99ca0ef00a6051d3170.png&amp;r=x&amp;s=140" width="24" />
            <a href="/maxatwork">maxatwork</a>
          </li>
          <li class="facebox-user-list-item">
            <img height="24" src="https://1.gravatar.com/avatar/fd3eb9bc4f137e48b1ed29a977d3c666?d=https%3A%2F%2Fidenticons.github.com%2Fb864650b74c76e359213ec9b1af77afe.png&amp;r=x&amp;s=140" width="24" />
            <a href="/kaiju">kaiju</a>
          </li>
          <li class="facebox-user-list-item">
            <img height="24" src="https://2.gravatar.com/avatar/98911c9d34157bbb0cf3dee5eddc6154?d=https%3A%2F%2Fidenticons.github.com%2F5e27aadc85f05f4119b07395a2194b33.png&amp;r=x&amp;s=140" width="24" />
            <a href="/mcasimir">mcasimir</a>
          </li>
          <li class="facebox-user-list-item">
            <img height="24" src="https://1.gravatar.com/avatar/7a6bf9e4c61f55f42e7717034014de1b?d=https%3A%2F%2Fidenticons.github.com%2F9518a28a408b58d2bfe0d121b7d4d687.png&amp;r=x&amp;s=140" width="24" />
            <a href="/DashausSP">DashausSP</a>
          </li>
          <li class="facebox-user-list-item">
            <img height="24" src="https://2.gravatar.com/avatar/4c97bbecf43064dc33208d838d30fe24?d=https%3A%2F%2Fidenticons.github.com%2F7cd0c47cadafb3c3a3670a1981c49a3b.png&amp;r=x&amp;s=140" width="24" />
            <a href="/alejandroiglesias">alejandroiglesias</a>
          </li>
      </ul>
    </div>
  </div>

<div id="files" class="bubble">
  <div class="file">
    <div class="meta">
      <div class="info">
        <span class="icon"><b class="octicon octicon-file-text"></b></span>
        <span class="mode" title="File Mode">file</span>
          <span>341 lines (292 sloc)</span>
        <span>9.65 kb</span>
      </div>
      <div class="actions">
        <div class="button-group">
            <a class="minibutton tooltipped leftwards js-conduit-openfile-check"
               href="http://mac.github.com"
               data-url="github-mac://openRepo/https://github.com/maxatwork/form2js?branch=master&amp;filepath=src%2Fform2js.js"
               title="Open this file in GitHub for Mac"
               data-failed-title="Your version of GitHub for Mac is too old to open this file. Try checking for updates.">
                <span class="octicon octicon-device-desktop"></span> Open
            </a>
                <a class="minibutton tooltipped upwards js-update-url-with-hash"
                   title="Clicking this button will automatically fork this project so you can edit the file"
                   href="/maxatwork/form2js/edit/master/src/form2js.js"
                   data-method="post" rel="nofollow">Edit</a>
          <a href="/maxatwork/form2js/raw/master/src/form2js.js" class="button minibutton " id="raw-url">Raw</a>
            <a href="/maxatwork/form2js/blame/master/src/form2js.js" class="button minibutton js-update-url-with-hash">Blame</a>
          <a href="/maxatwork/form2js/commits/master/src/form2js.js" class="button minibutton " rel="nofollow">History</a>
        </div><!-- /.button-group -->
          <a class="minibutton danger empty-icon tooltipped downwards"
             href="/maxatwork/form2js/delete/master/src/form2js.js"
             title="Fork this project and delete file"
             data-method="post" data-test-id="delete-blob-file" rel="nofollow">
          Delete
        </a>
      </div><!-- /.actions -->
    </div>
        <div class="blob-wrapper data type-javascript js-blob-data">
        <table class="file-code file-diff tab-size-8">
          <tr class="file-code-line">
            <td class="blob-line-nums">
              <span id="L1" rel="#L1">1</span>
<span id="L2" rel="#L2">2</span>
<span id="L3" rel="#L3">3</span>
<span id="L4" rel="#L4">4</span>
<span id="L5" rel="#L5">5</span>
<span id="L6" rel="#L6">6</span>
<span id="L7" rel="#L7">7</span>
<span id="L8" rel="#L8">8</span>
<span id="L9" rel="#L9">9</span>
<span id="L10" rel="#L10">10</span>
<span id="L11" rel="#L11">11</span>
<span id="L12" rel="#L12">12</span>
<span id="L13" rel="#L13">13</span>
<span id="L14" rel="#L14">14</span>
<span id="L15" rel="#L15">15</span>
<span id="L16" rel="#L16">16</span>
<span id="L17" rel="#L17">17</span>
<span id="L18" rel="#L18">18</span>
<span id="L19" rel="#L19">19</span>
<span id="L20" rel="#L20">20</span>
<span id="L21" rel="#L21">21</span>
<span id="L22" rel="#L22">22</span>
<span id="L23" rel="#L23">23</span>
<span id="L24" rel="#L24">24</span>
<span id="L25" rel="#L25">25</span>
<span id="L26" rel="#L26">26</span>
<span id="L27" rel="#L27">27</span>
<span id="L28" rel="#L28">28</span>
<span id="L29" rel="#L29">29</span>
<span id="L30" rel="#L30">30</span>
<span id="L31" rel="#L31">31</span>
<span id="L32" rel="#L32">32</span>
<span id="L33" rel="#L33">33</span>
<span id="L34" rel="#L34">34</span>
<span id="L35" rel="#L35">35</span>
<span id="L36" rel="#L36">36</span>
<span id="L37" rel="#L37">37</span>
<span id="L38" rel="#L38">38</span>
<span id="L39" rel="#L39">39</span>
<span id="L40" rel="#L40">40</span>
<span id="L41" rel="#L41">41</span>
<span id="L42" rel="#L42">42</span>
<span id="L43" rel="#L43">43</span>
<span id="L44" rel="#L44">44</span>
<span id="L45" rel="#L45">45</span>
<span id="L46" rel="#L46">46</span>
<span id="L47" rel="#L47">47</span>
<span id="L48" rel="#L48">48</span>
<span id="L49" rel="#L49">49</span>
<span id="L50" rel="#L50">50</span>
<span id="L51" rel="#L51">51</span>
<span id="L52" rel="#L52">52</span>
<span id="L53" rel="#L53">53</span>
<span id="L54" rel="#L54">54</span>
<span id="L55" rel="#L55">55</span>
<span id="L56" rel="#L56">56</span>
<span id="L57" rel="#L57">57</span>
<span id="L58" rel="#L58">58</span>
<span id="L59" rel="#L59">59</span>
<span id="L60" rel="#L60">60</span>
<span id="L61" rel="#L61">61</span>
<span id="L62" rel="#L62">62</span>
<span id="L63" rel="#L63">63</span>
<span id="L64" rel="#L64">64</span>
<span id="L65" rel="#L65">65</span>
<span id="L66" rel="#L66">66</span>
<span id="L67" rel="#L67">67</span>
<span id="L68" rel="#L68">68</span>
<span id="L69" rel="#L69">69</span>
<span id="L70" rel="#L70">70</span>
<span id="L71" rel="#L71">71</span>
<span id="L72" rel="#L72">72</span>
<span id="L73" rel="#L73">73</span>
<span id="L74" rel="#L74">74</span>
<span id="L75" rel="#L75">75</span>
<span id="L76" rel="#L76">76</span>
<span id="L77" rel="#L77">77</span>
<span id="L78" rel="#L78">78</span>
<span id="L79" rel="#L79">79</span>
<span id="L80" rel="#L80">80</span>
<span id="L81" rel="#L81">81</span>
<span id="L82" rel="#L82">82</span>
<span id="L83" rel="#L83">83</span>
<span id="L84" rel="#L84">84</span>
<span id="L85" rel="#L85">85</span>
<span id="L86" rel="#L86">86</span>
<span id="L87" rel="#L87">87</span>
<span id="L88" rel="#L88">88</span>
<span id="L89" rel="#L89">89</span>
<span id="L90" rel="#L90">90</span>
<span id="L91" rel="#L91">91</span>
<span id="L92" rel="#L92">92</span>
<span id="L93" rel="#L93">93</span>
<span id="L94" rel="#L94">94</span>
<span id="L95" rel="#L95">95</span>
<span id="L96" rel="#L96">96</span>
<span id="L97" rel="#L97">97</span>
<span id="L98" rel="#L98">98</span>
<span id="L99" rel="#L99">99</span>
<span id="L100" rel="#L100">100</span>
<span id="L101" rel="#L101">101</span>
<span id="L102" rel="#L102">102</span>
<span id="L103" rel="#L103">103</span>
<span id="L104" rel="#L104">104</span>
<span id="L105" rel="#L105">105</span>
<span id="L106" rel="#L106">106</span>
<span id="L107" rel="#L107">107</span>
<span id="L108" rel="#L108">108</span>
<span id="L109" rel="#L109">109</span>
<span id="L110" rel="#L110">110</span>
<span id="L111" rel="#L111">111</span>
<span id="L112" rel="#L112">112</span>
<span id="L113" rel="#L113">113</span>
<span id="L114" rel="#L114">114</span>
<span id="L115" rel="#L115">115</span>
<span id="L116" rel="#L116">116</span>
<span id="L117" rel="#L117">117</span>
<span id="L118" rel="#L118">118</span>
<span id="L119" rel="#L119">119</span>
<span id="L120" rel="#L120">120</span>
<span id="L121" rel="#L121">121</span>
<span id="L122" rel="#L122">122</span>
<span id="L123" rel="#L123">123</span>
<span id="L124" rel="#L124">124</span>
<span id="L125" rel="#L125">125</span>
<span id="L126" rel="#L126">126</span>
<span id="L127" rel="#L127">127</span>
<span id="L128" rel="#L128">128</span>
<span id="L129" rel="#L129">129</span>
<span id="L130" rel="#L130">130</span>
<span id="L131" rel="#L131">131</span>
<span id="L132" rel="#L132">132</span>
<span id="L133" rel="#L133">133</span>
<span id="L134" rel="#L134">134</span>
<span id="L135" rel="#L135">135</span>
<span id="L136" rel="#L136">136</span>
<span id="L137" rel="#L137">137</span>
<span id="L138" rel="#L138">138</span>
<span id="L139" rel="#L139">139</span>
<span id="L140" rel="#L140">140</span>
<span id="L141" rel="#L141">141</span>
<span id="L142" rel="#L142">142</span>
<span id="L143" rel="#L143">143</span>
<span id="L144" rel="#L144">144</span>
<span id="L145" rel="#L145">145</span>
<span id="L146" rel="#L146">146</span>
<span id="L147" rel="#L147">147</span>
<span id="L148" rel="#L148">148</span>
<span id="L149" rel="#L149">149</span>
<span id="L150" rel="#L150">150</span>
<span id="L151" rel="#L151">151</span>
<span id="L152" rel="#L152">152</span>
<span id="L153" rel="#L153">153</span>
<span id="L154" rel="#L154">154</span>
<span id="L155" rel="#L155">155</span>
<span id="L156" rel="#L156">156</span>
<span id="L157" rel="#L157">157</span>
<span id="L158" rel="#L158">158</span>
<span id="L159" rel="#L159">159</span>
<span id="L160" rel="#L160">160</span>
<span id="L161" rel="#L161">161</span>
<span id="L162" rel="#L162">162</span>
<span id="L163" rel="#L163">163</span>
<span id="L164" rel="#L164">164</span>
<span id="L165" rel="#L165">165</span>
<span id="L166" rel="#L166">166</span>
<span id="L167" rel="#L167">167</span>
<span id="L168" rel="#L168">168</span>
<span id="L169" rel="#L169">169</span>
<span id="L170" rel="#L170">170</span>
<span id="L171" rel="#L171">171</span>
<span id="L172" rel="#L172">172</span>
<span id="L173" rel="#L173">173</span>
<span id="L174" rel="#L174">174</span>
<span id="L175" rel="#L175">175</span>
<span id="L176" rel="#L176">176</span>
<span id="L177" rel="#L177">177</span>
<span id="L178" rel="#L178">178</span>
<span id="L179" rel="#L179">179</span>
<span id="L180" rel="#L180">180</span>
<span id="L181" rel="#L181">181</span>
<span id="L182" rel="#L182">182</span>
<span id="L183" rel="#L183">183</span>
<span id="L184" rel="#L184">184</span>
<span id="L185" rel="#L185">185</span>
<span id="L186" rel="#L186">186</span>
<span id="L187" rel="#L187">187</span>
<span id="L188" rel="#L188">188</span>
<span id="L189" rel="#L189">189</span>
<span id="L190" rel="#L190">190</span>
<span id="L191" rel="#L191">191</span>
<span id="L192" rel="#L192">192</span>
<span id="L193" rel="#L193">193</span>
<span id="L194" rel="#L194">194</span>
<span id="L195" rel="#L195">195</span>
<span id="L196" rel="#L196">196</span>
<span id="L197" rel="#L197">197</span>
<span id="L198" rel="#L198">198</span>
<span id="L199" rel="#L199">199</span>
<span id="L200" rel="#L200">200</span>
<span id="L201" rel="#L201">201</span>
<span id="L202" rel="#L202">202</span>
<span id="L203" rel="#L203">203</span>
<span id="L204" rel="#L204">204</span>
<span id="L205" rel="#L205">205</span>
<span id="L206" rel="#L206">206</span>
<span id="L207" rel="#L207">207</span>
<span id="L208" rel="#L208">208</span>
<span id="L209" rel="#L209">209</span>
<span id="L210" rel="#L210">210</span>
<span id="L211" rel="#L211">211</span>
<span id="L212" rel="#L212">212</span>
<span id="L213" rel="#L213">213</span>
<span id="L214" rel="#L214">214</span>
<span id="L215" rel="#L215">215</span>
<span id="L216" rel="#L216">216</span>
<span id="L217" rel="#L217">217</span>
<span id="L218" rel="#L218">218</span>
<span id="L219" rel="#L219">219</span>
<span id="L220" rel="#L220">220</span>
<span id="L221" rel="#L221">221</span>
<span id="L222" rel="#L222">222</span>
<span id="L223" rel="#L223">223</span>
<span id="L224" rel="#L224">224</span>
<span id="L225" rel="#L225">225</span>
<span id="L226" rel="#L226">226</span>
<span id="L227" rel="#L227">227</span>
<span id="L228" rel="#L228">228</span>
<span id="L229" rel="#L229">229</span>
<span id="L230" rel="#L230">230</span>
<span id="L231" rel="#L231">231</span>
<span id="L232" rel="#L232">232</span>
<span id="L233" rel="#L233">233</span>
<span id="L234" rel="#L234">234</span>
<span id="L235" rel="#L235">235</span>
<span id="L236" rel="#L236">236</span>
<span id="L237" rel="#L237">237</span>
<span id="L238" rel="#L238">238</span>
<span id="L239" rel="#L239">239</span>
<span id="L240" rel="#L240">240</span>
<span id="L241" rel="#L241">241</span>
<span id="L242" rel="#L242">242</span>
<span id="L243" rel="#L243">243</span>
<span id="L244" rel="#L244">244</span>
<span id="L245" rel="#L245">245</span>
<span id="L246" rel="#L246">246</span>
<span id="L247" rel="#L247">247</span>
<span id="L248" rel="#L248">248</span>
<span id="L249" rel="#L249">249</span>
<span id="L250" rel="#L250">250</span>
<span id="L251" rel="#L251">251</span>
<span id="L252" rel="#L252">252</span>
<span id="L253" rel="#L253">253</span>
<span id="L254" rel="#L254">254</span>
<span id="L255" rel="#L255">255</span>
<span id="L256" rel="#L256">256</span>
<span id="L257" rel="#L257">257</span>
<span id="L258" rel="#L258">258</span>
<span id="L259" rel="#L259">259</span>
<span id="L260" rel="#L260">260</span>
<span id="L261" rel="#L261">261</span>
<span id="L262" rel="#L262">262</span>
<span id="L263" rel="#L263">263</span>
<span id="L264" rel="#L264">264</span>
<span id="L265" rel="#L265">265</span>
<span id="L266" rel="#L266">266</span>
<span id="L267" rel="#L267">267</span>
<span id="L268" rel="#L268">268</span>
<span id="L269" rel="#L269">269</span>
<span id="L270" rel="#L270">270</span>
<span id="L271" rel="#L271">271</span>
<span id="L272" rel="#L272">272</span>
<span id="L273" rel="#L273">273</span>
<span id="L274" rel="#L274">274</span>
<span id="L275" rel="#L275">275</span>
<span id="L276" rel="#L276">276</span>
<span id="L277" rel="#L277">277</span>
<span id="L278" rel="#L278">278</span>
<span id="L279" rel="#L279">279</span>
<span id="L280" rel="#L280">280</span>
<span id="L281" rel="#L281">281</span>
<span id="L282" rel="#L282">282</span>
<span id="L283" rel="#L283">283</span>
<span id="L284" rel="#L284">284</span>
<span id="L285" rel="#L285">285</span>
<span id="L286" rel="#L286">286</span>
<span id="L287" rel="#L287">287</span>
<span id="L288" rel="#L288">288</span>
<span id="L289" rel="#L289">289</span>
<span id="L290" rel="#L290">290</span>
<span id="L291" rel="#L291">291</span>
<span id="L292" rel="#L292">292</span>
<span id="L293" rel="#L293">293</span>
<span id="L294" rel="#L294">294</span>
<span id="L295" rel="#L295">295</span>
<span id="L296" rel="#L296">296</span>
<span id="L297" rel="#L297">297</span>
<span id="L298" rel="#L298">298</span>
<span id="L299" rel="#L299">299</span>
<span id="L300" rel="#L300">300</span>
<span id="L301" rel="#L301">301</span>
<span id="L302" rel="#L302">302</span>
<span id="L303" rel="#L303">303</span>
<span id="L304" rel="#L304">304</span>
<span id="L305" rel="#L305">305</span>
<span id="L306" rel="#L306">306</span>
<span id="L307" rel="#L307">307</span>
<span id="L308" rel="#L308">308</span>
<span id="L309" rel="#L309">309</span>
<span id="L310" rel="#L310">310</span>
<span id="L311" rel="#L311">311</span>
<span id="L312" rel="#L312">312</span>
<span id="L313" rel="#L313">313</span>
<span id="L314" rel="#L314">314</span>
<span id="L315" rel="#L315">315</span>
<span id="L316" rel="#L316">316</span>
<span id="L317" rel="#L317">317</span>
<span id="L318" rel="#L318">318</span>
<span id="L319" rel="#L319">319</span>
<span id="L320" rel="#L320">320</span>
<span id="L321" rel="#L321">321</span>
<span id="L322" rel="#L322">322</span>
<span id="L323" rel="#L323">323</span>
<span id="L324" rel="#L324">324</span>
<span id="L325" rel="#L325">325</span>
<span id="L326" rel="#L326">326</span>
<span id="L327" rel="#L327">327</span>
<span id="L328" rel="#L328">328</span>
<span id="L329" rel="#L329">329</span>
<span id="L330" rel="#L330">330</span>
<span id="L331" rel="#L331">331</span>
<span id="L332" rel="#L332">332</span>
<span id="L333" rel="#L333">333</span>
<span id="L334" rel="#L334">334</span>
<span id="L335" rel="#L335">335</span>
<span id="L336" rel="#L336">336</span>
<span id="L337" rel="#L337">337</span>
<span id="L338" rel="#L338">338</span>
<span id="L339" rel="#L339">339</span>
<span id="L340" rel="#L340">340</span>

            </td>
            <td class="blob-line-code"><div class="code-body highlight"><pre><div class='line' id='LC1'><span class="cm">/**</span></div><div class='line' id='LC2'><span class="cm"> * Copyright (c) 2010 Maxim Vasiliev</span></div><div class='line' id='LC3'><span class="cm"> *</span></div><div class='line' id='LC4'><span class="cm"> * Permission is hereby granted, free of charge, to any person obtaining a copy</span></div><div class='line' id='LC5'><span class="cm"> * of this software and associated documentation files (the &quot;Software&quot;), to deal</span></div><div class='line' id='LC6'><span class="cm"> * in the Software without restriction, including without limitation the rights</span></div><div class='line' id='LC7'><span class="cm"> * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell</span></div><div class='line' id='LC8'><span class="cm"> * copies of the Software, and to permit persons to whom the Software is</span></div><div class='line' id='LC9'><span class="cm"> * furnished to do so, subject to the following conditions:</span></div><div class='line' id='LC10'><span class="cm"> *</span></div><div class='line' id='LC11'><span class="cm"> * The above copyright notice and this permission notice shall be included in</span></div><div class='line' id='LC12'><span class="cm"> * all copies or substantial portions of the Software.</span></div><div class='line' id='LC13'><span class="cm"> *</span></div><div class='line' id='LC14'><span class="cm"> * THE SOFTWARE IS PROVIDED &quot;AS IS&quot;, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR</span></div><div class='line' id='LC15'><span class="cm"> * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,</span></div><div class='line' id='LC16'><span class="cm"> * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE</span></div><div class='line' id='LC17'><span class="cm"> * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER</span></div><div class='line' id='LC18'><span class="cm"> * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,</span></div><div class='line' id='LC19'><span class="cm"> * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN</span></div><div class='line' id='LC20'><span class="cm"> * THE SOFTWARE.</span></div><div class='line' id='LC21'><span class="cm"> *</span></div><div class='line' id='LC22'><span class="cm"> * @author Maxim Vasiliev</span></div><div class='line' id='LC23'><span class="cm"> * Date: 09.09.2010</span></div><div class='line' id='LC24'><span class="cm"> * Time: 19:02:33</span></div><div class='line' id='LC25'><span class="cm"> */</span></div><div class='line' id='LC26'><br/></div><div class='line' id='LC27'><br/></div><div class='line' id='LC28'><span class="p">(</span><span class="kd">function</span> <span class="p">(</span><span class="nx">root</span><span class="p">,</span> <span class="nx">factory</span><span class="p">)</span></div><div class='line' id='LC29'><span class="p">{</span></div><div class='line' id='LC30'>	<span class="k">if</span> <span class="p">(</span><span class="k">typeof</span> <span class="nx">define</span> <span class="o">===</span> <span class="s1">&#39;function&#39;</span> <span class="o">&amp;&amp;</span> <span class="nx">define</span><span class="p">.</span><span class="nx">amd</span><span class="p">)</span></div><div class='line' id='LC31'>	<span class="p">{</span></div><div class='line' id='LC32'>		<span class="c1">// AMD. Register as an anonymous module.</span></div><div class='line' id='LC33'>		<span class="nx">define</span><span class="p">(</span><span class="nx">factory</span><span class="p">);</span></div><div class='line' id='LC34'>	<span class="p">}</span></div><div class='line' id='LC35'>	<span class="k">else</span></div><div class='line' id='LC36'>	<span class="p">{</span></div><div class='line' id='LC37'>		<span class="c1">// Browser globals</span></div><div class='line' id='LC38'>		<span class="nx">root</span><span class="p">.</span><span class="nx">form2js</span> <span class="o">=</span> <span class="nx">factory</span><span class="p">();</span></div><div class='line' id='LC39'>	<span class="p">}</span></div><div class='line' id='LC40'><span class="p">}(</span><span class="k">this</span><span class="p">,</span> <span class="kd">function</span> <span class="p">()</span></div><div class='line' id='LC41'><span class="p">{</span></div><div class='line' id='LC42'>	<span class="s2">&quot;use strict&quot;</span><span class="p">;</span></div><div class='line' id='LC43'><br/></div><div class='line' id='LC44'>	<span class="cm">/**</span></div><div class='line' id='LC45'><span class="cm">	 * Returns form values represented as Javascript object</span></div><div class='line' id='LC46'><span class="cm">	 * &quot;name&quot; attribute defines structure of resulting object</span></div><div class='line' id='LC47'><span class="cm">	 *</span></div><div class='line' id='LC48'><span class="cm">	 * @param rootNode {Element|String} root form element (or it&#39;s id) or array of root elements</span></div><div class='line' id='LC49'><span class="cm">	 * @param delimiter {String} structure parts delimiter defaults to &#39;.&#39;</span></div><div class='line' id='LC50'><span class="cm">	 * @param skipEmpty {Boolean} should skip empty text values, defaults to true</span></div><div class='line' id='LC51'><span class="cm">	 * @param nodeCallback {Function} custom function to get node value</span></div><div class='line' id='LC52'><span class="cm">	 * @param useIdIfEmptyName {Boolean} if true value of id attribute of field will be used if name of field is empty</span></div><div class='line' id='LC53'><span class="cm">	 */</span></div><div class='line' id='LC54'>	<span class="kd">function</span> <span class="nx">form2js</span><span class="p">(</span><span class="nx">rootNode</span><span class="p">,</span> <span class="nx">delimiter</span><span class="p">,</span> <span class="nx">skipEmpty</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">)</span></div><div class='line' id='LC55'>	<span class="p">{</span></div><div class='line' id='LC56'>		<span class="k">if</span> <span class="p">(</span><span class="k">typeof</span> <span class="nx">skipEmpty</span> <span class="o">==</span> <span class="s1">&#39;undefined&#39;</span> <span class="o">||</span> <span class="nx">skipEmpty</span> <span class="o">==</span> <span class="kc">null</span><span class="p">)</span> <span class="nx">skipEmpty</span> <span class="o">=</span> <span class="kc">true</span><span class="p">;</span></div><div class='line' id='LC57'>		<span class="k">if</span> <span class="p">(</span><span class="k">typeof</span> <span class="nx">delimiter</span> <span class="o">==</span> <span class="s1">&#39;undefined&#39;</span> <span class="o">||</span> <span class="nx">delimiter</span> <span class="o">==</span> <span class="kc">null</span><span class="p">)</span> <span class="nx">delimiter</span> <span class="o">=</span> <span class="s1">&#39;.&#39;</span><span class="p">;</span></div><div class='line' id='LC58'>		<span class="k">if</span> <span class="p">(</span><span class="nx">arguments</span><span class="p">.</span><span class="nx">length</span> <span class="o">&lt;</span> <span class="mi">5</span><span class="p">)</span> <span class="nx">useIdIfEmptyName</span> <span class="o">=</span> <span class="kc">false</span><span class="p">;</span></div><div class='line' id='LC59'><br/></div><div class='line' id='LC60'>		<span class="nx">rootNode</span> <span class="o">=</span> <span class="k">typeof</span> <span class="nx">rootNode</span> <span class="o">==</span> <span class="s1">&#39;string&#39;</span> <span class="o">?</span> <span class="nb">document</span><span class="p">.</span><span class="nx">getElementById</span><span class="p">(</span><span class="nx">rootNode</span><span class="p">)</span> <span class="o">:</span> <span class="nx">rootNode</span><span class="p">;</span></div><div class='line' id='LC61'><br/></div><div class='line' id='LC62'>		<span class="kd">var</span> <span class="nx">formValues</span> <span class="o">=</span> <span class="p">[],</span></div><div class='line' id='LC63'>			<span class="nx">currNode</span><span class="p">,</span></div><div class='line' id='LC64'>			<span class="nx">i</span> <span class="o">=</span> <span class="mi">0</span><span class="p">;</span></div><div class='line' id='LC65'><br/></div><div class='line' id='LC66'>		<span class="cm">/* If rootNode is array - combine values */</span></div><div class='line' id='LC67'>		<span class="k">if</span> <span class="p">(</span><span class="nx">rootNode</span><span class="p">.</span><span class="nx">constructor</span> <span class="o">==</span> <span class="nb">Array</span> <span class="o">||</span> <span class="p">(</span><span class="k">typeof</span> <span class="nx">NodeList</span> <span class="o">!=</span> <span class="s2">&quot;undefined&quot;</span> <span class="o">&amp;&amp;</span> <span class="nx">rootNode</span><span class="p">.</span><span class="nx">constructor</span> <span class="o">==</span> <span class="nx">NodeList</span><span class="p">))</span></div><div class='line' id='LC68'>		<span class="p">{</span></div><div class='line' id='LC69'>			<span class="k">while</span><span class="p">(</span><span class="nx">currNode</span> <span class="o">=</span> <span class="nx">rootNode</span><span class="p">[</span><span class="nx">i</span><span class="o">++</span><span class="p">])</span></div><div class='line' id='LC70'>			<span class="p">{</span></div><div class='line' id='LC71'>				<span class="nx">formValues</span> <span class="o">=</span> <span class="nx">formValues</span><span class="p">.</span><span class="nx">concat</span><span class="p">(</span><span class="nx">getFormValues</span><span class="p">(</span><span class="nx">currNode</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">));</span></div><div class='line' id='LC72'>			<span class="p">}</span></div><div class='line' id='LC73'>		<span class="p">}</span></div><div class='line' id='LC74'>		<span class="k">else</span></div><div class='line' id='LC75'>		<span class="p">{</span></div><div class='line' id='LC76'>			<span class="nx">formValues</span> <span class="o">=</span> <span class="nx">getFormValues</span><span class="p">(</span><span class="nx">rootNode</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">);</span></div><div class='line' id='LC77'>		<span class="p">}</span></div><div class='line' id='LC78'><br/></div><div class='line' id='LC79'>		<span class="k">return</span> <span class="nx">processNameValues</span><span class="p">(</span><span class="nx">formValues</span><span class="p">,</span> <span class="nx">skipEmpty</span><span class="p">,</span> <span class="nx">delimiter</span><span class="p">);</span></div><div class='line' id='LC80'>	<span class="p">}</span></div><div class='line' id='LC81'><br/></div><div class='line' id='LC82'>	<span class="cm">/**</span></div><div class='line' id='LC83'><span class="cm">	 * Processes collection of { name: &#39;name&#39;, value: &#39;value&#39; } objects.</span></div><div class='line' id='LC84'><span class="cm">	 * @param nameValues</span></div><div class='line' id='LC85'><span class="cm">	 * @param skipEmpty if true skips elements with value == &#39;&#39; or value == null</span></div><div class='line' id='LC86'><span class="cm">	 * @param delimiter</span></div><div class='line' id='LC87'><span class="cm">	 */</span></div><div class='line' id='LC88'>	<span class="kd">function</span> <span class="nx">processNameValues</span><span class="p">(</span><span class="nx">nameValues</span><span class="p">,</span> <span class="nx">skipEmpty</span><span class="p">,</span> <span class="nx">delimiter</span><span class="p">)</span></div><div class='line' id='LC89'>	<span class="p">{</span></div><div class='line' id='LC90'>		<span class="kd">var</span> <span class="nx">result</span> <span class="o">=</span> <span class="p">{},</span></div><div class='line' id='LC91'>			<span class="nx">arrays</span> <span class="o">=</span> <span class="p">{},</span></div><div class='line' id='LC92'>			<span class="nx">i</span><span class="p">,</span> <span class="nx">j</span><span class="p">,</span> <span class="nx">k</span><span class="p">,</span> <span class="nx">l</span><span class="p">,</span></div><div class='line' id='LC93'>			<span class="nx">value</span><span class="p">,</span></div><div class='line' id='LC94'>			<span class="nx">nameParts</span><span class="p">,</span></div><div class='line' id='LC95'>			<span class="nx">currResult</span><span class="p">,</span></div><div class='line' id='LC96'>			<span class="nx">arrNameFull</span><span class="p">,</span></div><div class='line' id='LC97'>			<span class="nx">arrName</span><span class="p">,</span></div><div class='line' id='LC98'>			<span class="nx">arrIdx</span><span class="p">,</span></div><div class='line' id='LC99'>			<span class="nx">namePart</span><span class="p">,</span></div><div class='line' id='LC100'>			<span class="nx">name</span><span class="p">,</span></div><div class='line' id='LC101'>			<span class="nx">_nameParts</span><span class="p">;</span></div><div class='line' id='LC102'><br/></div><div class='line' id='LC103'>		<span class="k">for</span> <span class="p">(</span><span class="nx">i</span> <span class="o">=</span> <span class="mi">0</span><span class="p">;</span> <span class="nx">i</span> <span class="o">&lt;</span> <span class="nx">nameValues</span><span class="p">.</span><span class="nx">length</span><span class="p">;</span> <span class="nx">i</span><span class="o">++</span><span class="p">)</span></div><div class='line' id='LC104'>		<span class="p">{</span></div><div class='line' id='LC105'>			<span class="nx">value</span> <span class="o">=</span> <span class="nx">nameValues</span><span class="p">[</span><span class="nx">i</span><span class="p">].</span><span class="nx">value</span><span class="p">;</span></div><div class='line' id='LC106'><br/></div><div class='line' id='LC107'>			<span class="k">if</span> <span class="p">(</span><span class="nx">skipEmpty</span> <span class="o">&amp;&amp;</span> <span class="p">(</span><span class="nx">value</span> <span class="o">===</span> <span class="s1">&#39;&#39;</span> <span class="o">||</span> <span class="nx">value</span> <span class="o">===</span> <span class="kc">null</span><span class="p">))</span> <span class="k">continue</span><span class="p">;</span></div><div class='line' id='LC108'><br/></div><div class='line' id='LC109'>			<span class="nx">name</span> <span class="o">=</span> <span class="nx">nameValues</span><span class="p">[</span><span class="nx">i</span><span class="p">].</span><span class="nx">name</span><span class="p">;</span></div><div class='line' id='LC110'>			<span class="nx">_nameParts</span> <span class="o">=</span> <span class="nx">name</span><span class="p">.</span><span class="nx">split</span><span class="p">(</span><span class="nx">delimiter</span><span class="p">);</span></div><div class='line' id='LC111'>			<span class="nx">nameParts</span> <span class="o">=</span> <span class="p">[];</span></div><div class='line' id='LC112'>			<span class="nx">currResult</span> <span class="o">=</span> <span class="nx">result</span><span class="p">;</span></div><div class='line' id='LC113'>			<span class="nx">arrNameFull</span> <span class="o">=</span> <span class="s1">&#39;&#39;</span><span class="p">;</span></div><div class='line' id='LC114'><br/></div><div class='line' id='LC115'>			<span class="k">for</span><span class="p">(</span><span class="nx">j</span> <span class="o">=</span> <span class="mi">0</span><span class="p">;</span> <span class="nx">j</span> <span class="o">&lt;</span> <span class="nx">_nameParts</span><span class="p">.</span><span class="nx">length</span><span class="p">;</span> <span class="nx">j</span><span class="o">++</span><span class="p">)</span></div><div class='line' id='LC116'>			<span class="p">{</span></div><div class='line' id='LC117'>				<span class="nx">namePart</span> <span class="o">=</span> <span class="nx">_nameParts</span><span class="p">[</span><span class="nx">j</span><span class="p">].</span><span class="nx">split</span><span class="p">(</span><span class="s1">&#39;][&#39;</span><span class="p">);</span></div><div class='line' id='LC118'>				<span class="k">if</span> <span class="p">(</span><span class="nx">namePart</span><span class="p">.</span><span class="nx">length</span> <span class="o">&gt;</span> <span class="mi">1</span><span class="p">)</span></div><div class='line' id='LC119'>				<span class="p">{</span></div><div class='line' id='LC120'>					<span class="k">for</span><span class="p">(</span><span class="nx">k</span> <span class="o">=</span> <span class="mi">0</span><span class="p">;</span> <span class="nx">k</span> <span class="o">&lt;</span> <span class="nx">namePart</span><span class="p">.</span><span class="nx">length</span><span class="p">;</span> <span class="nx">k</span><span class="o">++</span><span class="p">)</span></div><div class='line' id='LC121'>					<span class="p">{</span></div><div class='line' id='LC122'>						<span class="k">if</span> <span class="p">(</span><span class="nx">k</span> <span class="o">==</span> <span class="mi">0</span><span class="p">)</span></div><div class='line' id='LC123'>						<span class="p">{</span></div><div class='line' id='LC124'>							<span class="nx">namePart</span><span class="p">[</span><span class="nx">k</span><span class="p">]</span> <span class="o">=</span> <span class="nx">namePart</span><span class="p">[</span><span class="nx">k</span><span class="p">]</span> <span class="o">+</span> <span class="s1">&#39;]&#39;</span><span class="p">;</span></div><div class='line' id='LC125'>						<span class="p">}</span></div><div class='line' id='LC126'>						<span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="nx">k</span> <span class="o">==</span> <span class="nx">namePart</span><span class="p">.</span><span class="nx">length</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span></div><div class='line' id='LC127'>						<span class="p">{</span></div><div class='line' id='LC128'>							<span class="nx">namePart</span><span class="p">[</span><span class="nx">k</span><span class="p">]</span> <span class="o">=</span> <span class="s1">&#39;[&#39;</span> <span class="o">+</span> <span class="nx">namePart</span><span class="p">[</span><span class="nx">k</span><span class="p">];</span></div><div class='line' id='LC129'>						<span class="p">}</span></div><div class='line' id='LC130'>						<span class="k">else</span></div><div class='line' id='LC131'>						<span class="p">{</span></div><div class='line' id='LC132'>							<span class="nx">namePart</span><span class="p">[</span><span class="nx">k</span><span class="p">]</span> <span class="o">=</span> <span class="s1">&#39;[&#39;</span> <span class="o">+</span> <span class="nx">namePart</span><span class="p">[</span><span class="nx">k</span><span class="p">]</span> <span class="o">+</span> <span class="s1">&#39;]&#39;</span><span class="p">;</span></div><div class='line' id='LC133'>						<span class="p">}</span></div><div class='line' id='LC134'><br/></div><div class='line' id='LC135'>						<span class="nx">arrIdx</span> <span class="o">=</span> <span class="nx">namePart</span><span class="p">[</span><span class="nx">k</span><span class="p">].</span><span class="nx">match</span><span class="p">(</span><span class="sr">/([a-z_]+)?\[([a-z_][a-z0-9_]+?)\]/i</span><span class="p">);</span></div><div class='line' id='LC136'>						<span class="k">if</span> <span class="p">(</span><span class="nx">arrIdx</span><span class="p">)</span></div><div class='line' id='LC137'>						<span class="p">{</span></div><div class='line' id='LC138'>							<span class="k">for</span><span class="p">(</span><span class="nx">l</span> <span class="o">=</span> <span class="mi">1</span><span class="p">;</span> <span class="nx">l</span> <span class="o">&lt;</span> <span class="nx">arrIdx</span><span class="p">.</span><span class="nx">length</span><span class="p">;</span> <span class="nx">l</span><span class="o">++</span><span class="p">)</span></div><div class='line' id='LC139'>							<span class="p">{</span></div><div class='line' id='LC140'>								<span class="k">if</span> <span class="p">(</span><span class="nx">arrIdx</span><span class="p">[</span><span class="nx">l</span><span class="p">])</span> <span class="nx">nameParts</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">arrIdx</span><span class="p">[</span><span class="nx">l</span><span class="p">]);</span></div><div class='line' id='LC141'>							<span class="p">}</span></div><div class='line' id='LC142'>						<span class="p">}</span></div><div class='line' id='LC143'>						<span class="k">else</span><span class="p">{</span></div><div class='line' id='LC144'>							<span class="nx">nameParts</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">namePart</span><span class="p">[</span><span class="nx">k</span><span class="p">]);</span></div><div class='line' id='LC145'>						<span class="p">}</span></div><div class='line' id='LC146'>					<span class="p">}</span></div><div class='line' id='LC147'>				<span class="p">}</span></div><div class='line' id='LC148'>				<span class="k">else</span></div><div class='line' id='LC149'>					<span class="nx">nameParts</span> <span class="o">=</span> <span class="nx">nameParts</span><span class="p">.</span><span class="nx">concat</span><span class="p">(</span><span class="nx">namePart</span><span class="p">);</span></div><div class='line' id='LC150'>			<span class="p">}</span></div><div class='line' id='LC151'><br/></div><div class='line' id='LC152'>			<span class="k">for</span> <span class="p">(</span><span class="nx">j</span> <span class="o">=</span> <span class="mi">0</span><span class="p">;</span> <span class="nx">j</span> <span class="o">&lt;</span> <span class="nx">nameParts</span><span class="p">.</span><span class="nx">length</span><span class="p">;</span> <span class="nx">j</span><span class="o">++</span><span class="p">)</span></div><div class='line' id='LC153'>			<span class="p">{</span></div><div class='line' id='LC154'>				<span class="nx">namePart</span> <span class="o">=</span> <span class="nx">nameParts</span><span class="p">[</span><span class="nx">j</span><span class="p">];</span></div><div class='line' id='LC155'><br/></div><div class='line' id='LC156'>				<span class="k">if</span> <span class="p">(</span><span class="nx">namePart</span><span class="p">.</span><span class="nx">indexOf</span><span class="p">(</span><span class="s1">&#39;[]&#39;</span><span class="p">)</span> <span class="o">&gt;</span> <span class="o">-</span><span class="mi">1</span> <span class="o">&amp;&amp;</span> <span class="nx">j</span> <span class="o">==</span> <span class="nx">nameParts</span><span class="p">.</span><span class="nx">length</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span></div><div class='line' id='LC157'>				<span class="p">{</span></div><div class='line' id='LC158'>					<span class="nx">arrName</span> <span class="o">=</span> <span class="nx">namePart</span><span class="p">.</span><span class="nx">substr</span><span class="p">(</span><span class="mi">0</span><span class="p">,</span> <span class="nx">namePart</span><span class="p">.</span><span class="nx">indexOf</span><span class="p">(</span><span class="s1">&#39;[&#39;</span><span class="p">));</span></div><div class='line' id='LC159'>					<span class="nx">arrNameFull</span> <span class="o">+=</span> <span class="nx">arrName</span><span class="p">;</span></div><div class='line' id='LC160'><br/></div><div class='line' id='LC161'>					<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">])</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">]</span> <span class="o">=</span> <span class="p">[];</span></div><div class='line' id='LC162'>					<span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">].</span><span class="nx">push</span><span class="p">(</span><span class="nx">value</span><span class="p">);</span></div><div class='line' id='LC163'>				<span class="p">}</span></div><div class='line' id='LC164'>				<span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="nx">namePart</span><span class="p">.</span><span class="nx">indexOf</span><span class="p">(</span><span class="s1">&#39;[&#39;</span><span class="p">)</span> <span class="o">&gt;</span> <span class="o">-</span><span class="mi">1</span><span class="p">)</span></div><div class='line' id='LC165'>				<span class="p">{</span></div><div class='line' id='LC166'>					<span class="nx">arrName</span> <span class="o">=</span> <span class="nx">namePart</span><span class="p">.</span><span class="nx">substr</span><span class="p">(</span><span class="mi">0</span><span class="p">,</span> <span class="nx">namePart</span><span class="p">.</span><span class="nx">indexOf</span><span class="p">(</span><span class="s1">&#39;[&#39;</span><span class="p">));</span></div><div class='line' id='LC167'>					<span class="nx">arrIdx</span> <span class="o">=</span> <span class="nx">namePart</span><span class="p">.</span><span class="nx">replace</span><span class="p">(</span><span class="sr">/(^([a-z_]+)?\[)|(\]$)/gi</span><span class="p">,</span> <span class="s1">&#39;&#39;</span><span class="p">);</span></div><div class='line' id='LC168'><br/></div><div class='line' id='LC169'>					<span class="cm">/* Unique array name */</span></div><div class='line' id='LC170'>					<span class="nx">arrNameFull</span> <span class="o">+=</span> <span class="s1">&#39;_&#39;</span> <span class="o">+</span> <span class="nx">arrName</span> <span class="o">+</span> <span class="s1">&#39;_&#39;</span> <span class="o">+</span> <span class="nx">arrIdx</span><span class="p">;</span></div><div class='line' id='LC171'><br/></div><div class='line' id='LC172'>					<span class="cm">/*</span></div><div class='line' id='LC173'><span class="cm">					 * Because arrIdx in field name can be not zero-based and step can be</span></div><div class='line' id='LC174'><span class="cm">					 * other than 1, we can&#39;t use them in target array directly.</span></div><div class='line' id='LC175'><span class="cm">					 * Instead we&#39;re making a hash where key is arrIdx and value is a reference to</span></div><div class='line' id='LC176'><span class="cm">					 * added array element</span></div><div class='line' id='LC177'><span class="cm">					 */</span></div><div class='line' id='LC178'><br/></div><div class='line' id='LC179'>					<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">arrays</span><span class="p">[</span><span class="nx">arrNameFull</span><span class="p">])</span> <span class="nx">arrays</span><span class="p">[</span><span class="nx">arrNameFull</span><span class="p">]</span> <span class="o">=</span> <span class="p">{};</span></div><div class='line' id='LC180'>					<span class="k">if</span> <span class="p">(</span><span class="nx">arrName</span> <span class="o">!=</span> <span class="s1">&#39;&#39;</span> <span class="o">&amp;&amp;</span> <span class="o">!</span><span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">])</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">]</span> <span class="o">=</span> <span class="p">[];</span></div><div class='line' id='LC181'><br/></div><div class='line' id='LC182'>					<span class="k">if</span> <span class="p">(</span><span class="nx">j</span> <span class="o">==</span> <span class="nx">nameParts</span><span class="p">.</span><span class="nx">length</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span></div><div class='line' id='LC183'>					<span class="p">{</span></div><div class='line' id='LC184'>						<span class="k">if</span> <span class="p">(</span><span class="nx">arrName</span> <span class="o">==</span> <span class="s1">&#39;&#39;</span><span class="p">)</span></div><div class='line' id='LC185'>						<span class="p">{</span></div><div class='line' id='LC186'>							<span class="nx">currResult</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">value</span><span class="p">);</span></div><div class='line' id='LC187'>							<span class="nx">arrays</span><span class="p">[</span><span class="nx">arrNameFull</span><span class="p">][</span><span class="nx">arrIdx</span><span class="p">]</span> <span class="o">=</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">currResult</span><span class="p">.</span><span class="nx">length</span> <span class="o">-</span> <span class="mi">1</span><span class="p">];</span></div><div class='line' id='LC188'>						<span class="p">}</span></div><div class='line' id='LC189'>						<span class="k">else</span></div><div class='line' id='LC190'>						<span class="p">{</span></div><div class='line' id='LC191'>							<span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">].</span><span class="nx">push</span><span class="p">(</span><span class="nx">value</span><span class="p">);</span></div><div class='line' id='LC192'>							<span class="nx">arrays</span><span class="p">[</span><span class="nx">arrNameFull</span><span class="p">][</span><span class="nx">arrIdx</span><span class="p">]</span> <span class="o">=</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">][</span><span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">].</span><span class="nx">length</span> <span class="o">-</span> <span class="mi">1</span><span class="p">];</span></div><div class='line' id='LC193'>						<span class="p">}</span></div><div class='line' id='LC194'>					<span class="p">}</span></div><div class='line' id='LC195'>					<span class="k">else</span></div><div class='line' id='LC196'>					<span class="p">{</span></div><div class='line' id='LC197'>						<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">arrays</span><span class="p">[</span><span class="nx">arrNameFull</span><span class="p">][</span><span class="nx">arrIdx</span><span class="p">])</span></div><div class='line' id='LC198'>						<span class="p">{</span></div><div class='line' id='LC199'>							<span class="k">if</span> <span class="p">((</span><span class="sr">/^[0-9a-z_]+\[?/i</span><span class="p">).</span><span class="nx">test</span><span class="p">(</span><span class="nx">nameParts</span><span class="p">[</span><span class="nx">j</span><span class="o">+</span><span class="mi">1</span><span class="p">]))</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">].</span><span class="nx">push</span><span class="p">({});</span></div><div class='line' id='LC200'>							<span class="k">else</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">].</span><span class="nx">push</span><span class="p">([]);</span></div><div class='line' id='LC201'><br/></div><div class='line' id='LC202'>							<span class="nx">arrays</span><span class="p">[</span><span class="nx">arrNameFull</span><span class="p">][</span><span class="nx">arrIdx</span><span class="p">]</span> <span class="o">=</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">][</span><span class="nx">currResult</span><span class="p">[</span><span class="nx">arrName</span><span class="p">].</span><span class="nx">length</span> <span class="o">-</span> <span class="mi">1</span><span class="p">];</span></div><div class='line' id='LC203'>						<span class="p">}</span></div><div class='line' id='LC204'>					<span class="p">}</span></div><div class='line' id='LC205'><br/></div><div class='line' id='LC206'>					<span class="nx">currResult</span> <span class="o">=</span> <span class="nx">arrays</span><span class="p">[</span><span class="nx">arrNameFull</span><span class="p">][</span><span class="nx">arrIdx</span><span class="p">];</span></div><div class='line' id='LC207'>				<span class="p">}</span></div><div class='line' id='LC208'>				<span class="k">else</span></div><div class='line' id='LC209'>				<span class="p">{</span></div><div class='line' id='LC210'>					<span class="nx">arrNameFull</span> <span class="o">+=</span> <span class="nx">namePart</span><span class="p">;</span></div><div class='line' id='LC211'><br/></div><div class='line' id='LC212'>					<span class="k">if</span> <span class="p">(</span><span class="nx">j</span> <span class="o">&lt;</span> <span class="nx">nameParts</span><span class="p">.</span><span class="nx">length</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span> <span class="cm">/* Not the last part of name - means object */</span></div><div class='line' id='LC213'>					<span class="p">{</span></div><div class='line' id='LC214'>						<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">currResult</span><span class="p">[</span><span class="nx">namePart</span><span class="p">])</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">namePart</span><span class="p">]</span> <span class="o">=</span> <span class="p">{};</span></div><div class='line' id='LC215'>						<span class="nx">currResult</span> <span class="o">=</span> <span class="nx">currResult</span><span class="p">[</span><span class="nx">namePart</span><span class="p">];</span></div><div class='line' id='LC216'>					<span class="p">}</span></div><div class='line' id='LC217'>					<span class="k">else</span></div><div class='line' id='LC218'>					<span class="p">{</span></div><div class='line' id='LC219'>						<span class="nx">currResult</span><span class="p">[</span><span class="nx">namePart</span><span class="p">]</span> <span class="o">=</span> <span class="nx">value</span><span class="p">;</span></div><div class='line' id='LC220'>					<span class="p">}</span></div><div class='line' id='LC221'>				<span class="p">}</span></div><div class='line' id='LC222'>			<span class="p">}</span></div><div class='line' id='LC223'>		<span class="p">}</span></div><div class='line' id='LC224'><br/></div><div class='line' id='LC225'>		<span class="k">return</span> <span class="nx">result</span><span class="p">;</span></div><div class='line' id='LC226'>	<span class="p">}</span></div><div class='line' id='LC227'><br/></div><div class='line' id='LC228'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">function</span> <span class="nx">getFormValues</span><span class="p">(</span><span class="nx">rootNode</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">)</span></div><div class='line' id='LC229'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="p">{</span></div><div class='line' id='LC230'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">var</span> <span class="nx">result</span> <span class="o">=</span> <span class="nx">extractNodeValues</span><span class="p">(</span><span class="nx">rootNode</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">);</span></div><div class='line' id='LC231'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">return</span> <span class="nx">result</span><span class="p">.</span><span class="nx">length</span> <span class="o">&gt;</span> <span class="mi">0</span> <span class="o">?</span> <span class="nx">result</span> <span class="o">:</span> <span class="nx">getSubFormValues</span><span class="p">(</span><span class="nx">rootNode</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">);</span></div><div class='line' id='LC232'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="p">}</span></div><div class='line' id='LC233'><br/></div><div class='line' id='LC234'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">function</span> <span class="nx">getSubFormValues</span><span class="p">(</span><span class="nx">rootNode</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">)</span></div><div class='line' id='LC235'>	<span class="p">{</span></div><div class='line' id='LC236'>		<span class="kd">var</span> <span class="nx">result</span> <span class="o">=</span> <span class="p">[],</span></div><div class='line' id='LC237'>			<span class="nx">currentNode</span> <span class="o">=</span> <span class="nx">rootNode</span><span class="p">.</span><span class="nx">firstChild</span><span class="p">;</span></div><div class='line' id='LC238'><br/></div><div class='line' id='LC239'>		<span class="k">while</span> <span class="p">(</span><span class="nx">currentNode</span><span class="p">)</span></div><div class='line' id='LC240'>		<span class="p">{</span></div><div class='line' id='LC241'>			<span class="nx">result</span> <span class="o">=</span> <span class="nx">result</span><span class="p">.</span><span class="nx">concat</span><span class="p">(</span><span class="nx">extractNodeValues</span><span class="p">(</span><span class="nx">currentNode</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">));</span></div><div class='line' id='LC242'>			<span class="nx">currentNode</span> <span class="o">=</span> <span class="nx">currentNode</span><span class="p">.</span><span class="nx">nextSibling</span><span class="p">;</span></div><div class='line' id='LC243'>		<span class="p">}</span></div><div class='line' id='LC244'><br/></div><div class='line' id='LC245'>		<span class="k">return</span> <span class="nx">result</span><span class="p">;</span></div><div class='line' id='LC246'>	<span class="p">}</span></div><div class='line' id='LC247'><br/></div><div class='line' id='LC248'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">function</span> <span class="nx">extractNodeValues</span><span class="p">(</span><span class="nx">node</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC249'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">if</span> <span class="p">(</span><span class="nx">node</span><span class="p">.</span><span class="nx">disabled</span><span class="p">)</span> <span class="k">return</span> <span class="p">[];</span></div><div class='line' id='LC250'><br/></div><div class='line' id='LC251'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">var</span> <span class="nx">callbackResult</span><span class="p">,</span> <span class="nx">fieldValue</span><span class="p">,</span> <span class="nx">result</span><span class="p">,</span> <span class="nx">fieldName</span> <span class="o">=</span> <span class="nx">getFieldName</span><span class="p">(</span><span class="nx">node</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">);</span></div><div class='line' id='LC252'><br/></div><div class='line' id='LC253'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="nx">callbackResult</span> <span class="o">=</span> <span class="nx">nodeCallback</span> <span class="o">&amp;&amp;</span> <span class="nx">nodeCallback</span><span class="p">(</span><span class="nx">node</span><span class="p">);</span></div><div class='line' id='LC254'><br/></div><div class='line' id='LC255'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">if</span> <span class="p">(</span><span class="nx">callbackResult</span> <span class="o">&amp;&amp;</span> <span class="nx">callbackResult</span><span class="p">.</span><span class="nx">name</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC256'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="nx">result</span> <span class="o">=</span> <span class="p">[</span><span class="nx">callbackResult</span><span class="p">];</span></div><div class='line' id='LC257'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="p">}</span></div><div class='line' id='LC258'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="nx">fieldName</span> <span class="o">!=</span> <span class="s1">&#39;&#39;</span> <span class="o">&amp;&amp;</span> <span class="nx">node</span><span class="p">.</span><span class="nx">nodeName</span><span class="p">.</span><span class="nx">match</span><span class="p">(</span><span class="sr">/INPUT|TEXTAREA/i</span><span class="p">))</span> <span class="p">{</span></div><div class='line' id='LC259'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="nx">fieldValue</span> <span class="o">=</span> <span class="nx">getFieldValue</span><span class="p">(</span><span class="nx">node</span><span class="p">);</span></div><div class='line' id='LC260'>			<span class="nx">result</span> <span class="o">=</span> <span class="p">[</span> <span class="p">{</span> <span class="nx">name</span><span class="o">:</span> <span class="nx">fieldName</span><span class="p">,</span> <span class="nx">value</span><span class="o">:</span> <span class="nx">fieldValue</span><span class="p">}</span> <span class="p">];</span></div><div class='line' id='LC261'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="p">}</span></div><div class='line' id='LC262'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="nx">fieldName</span> <span class="o">!=</span> <span class="s1">&#39;&#39;</span> <span class="o">&amp;&amp;</span> <span class="nx">node</span><span class="p">.</span><span class="nx">nodeName</span><span class="p">.</span><span class="nx">match</span><span class="p">(</span><span class="sr">/SELECT/i</span><span class="p">))</span> <span class="p">{</span></div><div class='line' id='LC263'>	        <span class="nx">fieldValue</span> <span class="o">=</span> <span class="nx">getFieldValue</span><span class="p">(</span><span class="nx">node</span><span class="p">);</span></div><div class='line' id='LC264'>	        <span class="nx">result</span> <span class="o">=</span> <span class="p">[</span> <span class="p">{</span> <span class="nx">name</span><span class="o">:</span> <span class="nx">fieldName</span><span class="p">.</span><span class="nx">replace</span><span class="p">(</span><span class="sr">/\[\]$/</span><span class="p">,</span> <span class="s1">&#39;&#39;</span><span class="p">),</span> <span class="nx">value</span><span class="o">:</span> <span class="nx">fieldValue</span> <span class="p">}</span> <span class="p">];</span></div><div class='line' id='LC265'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="p">}</span></div><div class='line' id='LC266'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC267'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="nx">result</span> <span class="o">=</span> <span class="nx">getSubFormValues</span><span class="p">(</span><span class="nx">node</span><span class="p">,</span> <span class="nx">nodeCallback</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">);</span></div><div class='line' id='LC268'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="p">}</span></div><div class='line' id='LC269'><br/></div><div class='line' id='LC270'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">return</span> <span class="nx">result</span><span class="p">;</span></div><div class='line' id='LC271'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="p">}</span></div><div class='line' id='LC272'><br/></div><div class='line' id='LC273'>	<span class="kd">function</span> <span class="nx">getFieldName</span><span class="p">(</span><span class="nx">node</span><span class="p">,</span> <span class="nx">useIdIfEmptyName</span><span class="p">)</span></div><div class='line' id='LC274'>	<span class="p">{</span></div><div class='line' id='LC275'>		<span class="k">if</span> <span class="p">(</span><span class="nx">node</span><span class="p">.</span><span class="nx">name</span> <span class="o">&amp;&amp;</span> <span class="nx">node</span><span class="p">.</span><span class="nx">name</span> <span class="o">!=</span> <span class="s1">&#39;&#39;</span><span class="p">)</span> <span class="k">return</span> <span class="nx">node</span><span class="p">.</span><span class="nx">name</span><span class="p">;</span></div><div class='line' id='LC276'>		<span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="nx">useIdIfEmptyName</span> <span class="o">&amp;&amp;</span> <span class="nx">node</span><span class="p">.</span><span class="nx">id</span> <span class="o">&amp;&amp;</span> <span class="nx">node</span><span class="p">.</span><span class="nx">id</span> <span class="o">!=</span> <span class="s1">&#39;&#39;</span><span class="p">)</span> <span class="k">return</span> <span class="nx">node</span><span class="p">.</span><span class="nx">id</span><span class="p">;</span></div><div class='line' id='LC277'>		<span class="k">else</span> <span class="k">return</span> <span class="s1">&#39;&#39;</span><span class="p">;</span></div><div class='line' id='LC278'>	<span class="p">}</span></div><div class='line' id='LC279'><br/></div><div class='line' id='LC280'><br/></div><div class='line' id='LC281'>	<span class="kd">function</span> <span class="nx">getFieldValue</span><span class="p">(</span><span class="nx">fieldNode</span><span class="p">)</span></div><div class='line' id='LC282'>	<span class="p">{</span></div><div class='line' id='LC283'>		<span class="k">if</span> <span class="p">(</span><span class="nx">fieldNode</span><span class="p">.</span><span class="nx">disabled</span><span class="p">)</span> <span class="k">return</span> <span class="kc">null</span><span class="p">;</span></div><div class='line' id='LC284'><br/></div><div class='line' id='LC285'>		<span class="k">switch</span> <span class="p">(</span><span class="nx">fieldNode</span><span class="p">.</span><span class="nx">nodeName</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC286'>			<span class="k">case</span> <span class="s1">&#39;INPUT&#39;</span><span class="o">:</span></div><div class='line' id='LC287'>			<span class="k">case</span> <span class="s1">&#39;TEXTAREA&#39;</span><span class="o">:</span></div><div class='line' id='LC288'>				<span class="k">switch</span> <span class="p">(</span><span class="nx">fieldNode</span><span class="p">.</span><span class="nx">type</span><span class="p">.</span><span class="nx">toLowerCase</span><span class="p">())</span> <span class="p">{</span></div><div class='line' id='LC289'>					<span class="k">case</span> <span class="s1">&#39;radio&#39;</span><span class="o">:</span></div><div class='line' id='LC290'>			<span class="k">if</span> <span class="p">(</span><span class="nx">fieldNode</span><span class="p">.</span><span class="nx">checked</span> <span class="o">&amp;&amp;</span> <span class="nx">fieldNode</span><span class="p">.</span><span class="nx">value</span> <span class="o">===</span> <span class="s2">&quot;false&quot;</span><span class="p">)</span> <span class="k">return</span> <span class="kc">false</span><span class="p">;</span></div><div class='line' id='LC291'>					<span class="k">case</span> <span class="s1">&#39;checkbox&#39;</span><span class="o">:</span></div><div class='line' id='LC292'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">if</span> <span class="p">(</span><span class="nx">fieldNode</span><span class="p">.</span><span class="nx">checked</span> <span class="o">&amp;&amp;</span> <span class="nx">fieldNode</span><span class="p">.</span><span class="nx">value</span> <span class="o">===</span> <span class="s2">&quot;true&quot;</span><span class="p">)</span> <span class="k">return</span> <span class="kc">true</span><span class="p">;</span></div><div class='line' id='LC293'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">fieldNode</span><span class="p">.</span><span class="nx">checked</span> <span class="o">&amp;&amp;</span> <span class="nx">fieldNode</span><span class="p">.</span><span class="nx">value</span> <span class="o">===</span> <span class="s2">&quot;true&quot;</span><span class="p">)</span> <span class="k">return</span> <span class="kc">false</span><span class="p">;</span></div><div class='line' id='LC294'>			<span class="k">if</span> <span class="p">(</span><span class="nx">fieldNode</span><span class="p">.</span><span class="nx">checked</span><span class="p">)</span> <span class="k">return</span> <span class="nx">fieldNode</span><span class="p">.</span><span class="nx">value</span><span class="p">;</span></div><div class='line' id='LC295'>						<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC296'><br/></div><div class='line' id='LC297'>					<span class="k">case</span> <span class="s1">&#39;button&#39;</span><span class="o">:</span></div><div class='line' id='LC298'>					<span class="k">case</span> <span class="s1">&#39;reset&#39;</span><span class="o">:</span></div><div class='line' id='LC299'>					<span class="k">case</span> <span class="s1">&#39;submit&#39;</span><span class="o">:</span></div><div class='line' id='LC300'>					<span class="k">case</span> <span class="s1">&#39;image&#39;</span><span class="o">:</span></div><div class='line' id='LC301'>						<span class="k">return</span> <span class="s1">&#39;&#39;</span><span class="p">;</span></div><div class='line' id='LC302'>						<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC303'><br/></div><div class='line' id='LC304'>					<span class="k">default</span><span class="o">:</span></div><div class='line' id='LC305'>						<span class="k">return</span> <span class="nx">fieldNode</span><span class="p">.</span><span class="nx">value</span><span class="p">;</span></div><div class='line' id='LC306'>						<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC307'>				<span class="p">}</span></div><div class='line' id='LC308'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC309'><br/></div><div class='line' id='LC310'>			<span class="k">case</span> <span class="s1">&#39;SELECT&#39;</span><span class="o">:</span></div><div class='line' id='LC311'>				<span class="k">return</span> <span class="nx">getSelectedOptionValue</span><span class="p">(</span><span class="nx">fieldNode</span><span class="p">);</span></div><div class='line' id='LC312'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC313'><br/></div><div class='line' id='LC314'>			<span class="k">default</span><span class="o">:</span></div><div class='line' id='LC315'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC316'>		<span class="p">}</span></div><div class='line' id='LC317'><br/></div><div class='line' id='LC318'>		<span class="k">return</span> <span class="kc">null</span><span class="p">;</span></div><div class='line' id='LC319'>	<span class="p">}</span></div><div class='line' id='LC320'><br/></div><div class='line' id='LC321'>	<span class="kd">function</span> <span class="nx">getSelectedOptionValue</span><span class="p">(</span><span class="nx">selectNode</span><span class="p">)</span></div><div class='line' id='LC322'>	<span class="p">{</span></div><div class='line' id='LC323'>		<span class="kd">var</span> <span class="nx">multiple</span> <span class="o">=</span> <span class="nx">selectNode</span><span class="p">.</span><span class="nx">multiple</span><span class="p">,</span></div><div class='line' id='LC324'>			<span class="nx">result</span> <span class="o">=</span> <span class="p">[],</span></div><div class='line' id='LC325'>			<span class="nx">options</span><span class="p">,</span></div><div class='line' id='LC326'>			<span class="nx">i</span><span class="p">,</span> <span class="nx">l</span><span class="p">;</span></div><div class='line' id='LC327'><br/></div><div class='line' id='LC328'>		<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">multiple</span><span class="p">)</span> <span class="k">return</span> <span class="nx">selectNode</span><span class="p">.</span><span class="nx">value</span><span class="p">;</span></div><div class='line' id='LC329'><br/></div><div class='line' id='LC330'>		<span class="k">for</span> <span class="p">(</span><span class="nx">options</span> <span class="o">=</span> <span class="nx">selectNode</span><span class="p">.</span><span class="nx">getElementsByTagName</span><span class="p">(</span><span class="s2">&quot;option&quot;</span><span class="p">),</span> <span class="nx">i</span> <span class="o">=</span> <span class="mi">0</span><span class="p">,</span> <span class="nx">l</span> <span class="o">=</span> <span class="nx">options</span><span class="p">.</span><span class="nx">length</span><span class="p">;</span> <span class="nx">i</span> <span class="o">&lt;</span> <span class="nx">l</span><span class="p">;</span> <span class="nx">i</span><span class="o">++</span><span class="p">)</span></div><div class='line' id='LC331'>		<span class="p">{</span></div><div class='line' id='LC332'>			<span class="k">if</span> <span class="p">(</span><span class="nx">options</span><span class="p">[</span><span class="nx">i</span><span class="p">].</span><span class="nx">selected</span><span class="p">)</span> <span class="nx">result</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">options</span><span class="p">[</span><span class="nx">i</span><span class="p">].</span><span class="nx">value</span><span class="p">);</span></div><div class='line' id='LC333'>		<span class="p">}</span></div><div class='line' id='LC334'><br/></div><div class='line' id='LC335'>		<span class="k">return</span> <span class="nx">result</span><span class="p">;</span></div><div class='line' id='LC336'>	<span class="p">}</span></div><div class='line' id='LC337'><br/></div><div class='line' id='LC338'>	<span class="k">return</span> <span class="nx">form2js</span><span class="p">;</span></div><div class='line' id='LC339'><br/></div><div class='line' id='LC340'><span class="p">}));</span></div></pre></div></td>
          </tr>
        </table>
  </div>

  </div>
</div>

<a href="#jump-to-line" rel="facebox[.linejump]" data-hotkey="l" class="js-jump-to-line" style="display:none">Jump to Line</a>
<div id="jump-to-line" style="display:none">
  <form accept-charset="UTF-8" class="js-jump-to-line-form">
    <input class="linejump-input js-jump-to-line-field" type="text" placeholder="Jump to line&hellip;" autofocus>
    <button type="submit" class="button">Go</button>
  </form>
</div>

        </div>

      </div><!-- /.repo-container -->
      <div class="modal-backdrop"></div>
    </div><!-- /.container -->
  </div><!-- /.site -->


    </div><!-- /.wrapper -->

      <div class="container">
  <div class="site-footer">
    <ul class="site-footer-links right">
      <li><a href="https://status.github.com/">Status</a></li>
      <li><a href="http://developer.github.com">API</a></li>
      <li><a href="http://training.github.com">Training</a></li>
      <li><a href="http://shop.github.com">Shop</a></li>
      <li><a href="/blog">Blog</a></li>
      <li><a href="/about">About</a></li>

    </ul>

    <a href="/">
      <span class="mega-octicon octicon-mark-github" title="GitHub"></span>
    </a>

    <ul class="site-footer-links">
      <li>&copy; 2014 <span title="0.04817s from github-fe119-cp1-prd.iad.github.net">GitHub</span>, Inc.</li>
        <li><a href="/site/terms">Terms</a></li>
        <li><a href="/site/privacy">Privacy</a></li>
        <li><a href="/security">Security</a></li>
        <li><a href="/contact">Contact</a></li>
    </ul>
  </div><!-- /.site-footer -->
</div><!-- /.container -->


    <div class="fullscreen-overlay js-fullscreen-overlay" id="fullscreen_overlay">
  <div class="fullscreen-container js-fullscreen-container">
    <div class="textarea-wrap">
      <textarea name="fullscreen-contents" id="fullscreen-contents" class="js-fullscreen-contents" placeholder="" data-suggester="fullscreen_suggester"></textarea>
          <div class="suggester-container">
              <div class="suggester fullscreen-suggester js-navigation-container" id="fullscreen_suggester"
                 data-url="/maxatwork/form2js/suggestions/commit">
              </div>
          </div>
    </div>
  </div>
  <div class="fullscreen-sidebar">
    <a href="#" class="exit-fullscreen js-exit-fullscreen tooltipped leftwards" title="Exit Zen Mode">
      <span class="mega-octicon octicon-screen-normal"></span>
    </a>
    <a href="#" class="theme-switcher js-theme-switcher tooltipped leftwards"
      title="Switch themes">
      <span class="octicon octicon-color-mode"></span>
    </a>
  </div>
</div>



    <div id="ajax-error-message" class="flash flash-error">
      <span class="octicon octicon-alert"></span>
      <a href="#" class="octicon octicon-remove-close close js-ajax-error-dismiss"></a>
      Something went wrong with that request. Please try again.
    </div>

  </body>
</html>


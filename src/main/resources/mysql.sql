  


<!DOCTYPE html>
<html>
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# githubog: http://ogp.me/ns/fb/githubog#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>logback/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql at master · qos-ch/logback</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />
    <link rel="apple-touch-icon" sizes="57x57" href="/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-144.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144.png" />
    <link rel="logo" type="image/svg" href="http://github-media-downloads.s3.amazonaws.com/github-logo.svg" />
    <link rel="xhr-socket" href="/_sockets" />


    <meta name="msapplication-TileImage" content="/windows-tile.png" />
    <meta name="msapplication-TileColor" content="#ffffff" />
    <meta name="selected-link" value="repo_source" data-pjax-transient />

    
    
    <link rel="icon" type="image/x-icon" href="/favicon.ico" />

    <meta content="authenticity_token" name="csrf-param" />
<meta content="ZXCayntwiqm4nSALzj+V+zwIb86bQKNlxxm1Cr/gkpc=" name="csrf-token" />

    <link href="https://a248.e.akamai.net/assets.github.com/assets/github-d5919c2c85462b6c3d5b24d94361e0cfe7c6d3c1.css" media="all" rel="stylesheet" type="text/css" />
    <link href="https://a248.e.akamai.net/assets.github.com/assets/github2-c199e1478f7546c043f0929b27d7f1688483a16d.css" media="all" rel="stylesheet" type="text/css" />
    


      <script src="https://a248.e.akamai.net/assets.github.com/assets/frameworks-92d138f450f2960501e28397a2f63b0f100590f0.js" type="text/javascript"></script>
      <script src="https://a248.e.akamai.net/assets.github.com/assets/github-c0439b281928dd009b3a75e187d4b94c7abe5890.js" type="text/javascript"></script>
      
      <meta http-equiv="x-pjax-version" content="14f79f6751c8415cf388e9730a05fea0">

        <link data-pjax-transient rel='permalink' href='/qos-ch/logback/blob/e6319e5fb631d220f23829981e0ad8a13d262ed3/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql'>
    <meta property="og:title" content="logback"/>
    <meta property="og:type" content="githubog:gitrepository"/>
    <meta property="og:url" content="https://github.com/qos-ch/logback"/>
    <meta property="og:image" content="https://secure.gravatar.com/avatar/fe2dcc1245366bd07c792322a80589ee?s=420&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png"/>
    <meta property="og:site_name" content="GitHub"/>
    <meta property="og:description" content="logback - The reliable, generic, fast and flexible logging framework for Java."/>
    <meta property="twitter:card" content="summary"/>
    <meta property="twitter:site" content="@GitHub">
    <meta property="twitter:title" content="qos-ch/logback"/>

    <meta name="description" content="logback - The reliable, generic, fast and flexible logging framework for Java." />

  <link href="https://github.com/qos-ch/logback/commits/master.atom" rel="alternate" title="Recent Commits to logback:master" type="application/atom+xml" />

  </head>


  <body class="logged_in page-blob windows vis-public env-production  ">
    <div id="wrapper">

      

      
      
      

      <div class="header header-logged-in true">
  <div class="container clearfix">

    <a class="header-logo-invertocat" href="https://github.com/">
  <span class="mega-icon mega-icon-invertocat"></span>
</a>

    <div class="divider-vertical"></div>

      <a href="/notifications" class="notification-indicator tooltipped downwards" title="You have no unread notifications">
    <span class="mail-status all-read"></span>
  </a>
  <div class="divider-vertical"></div>


      <div class="command-bar js-command-bar  in-repository">
          <form accept-charset="UTF-8" action="/search" class="command-bar-form" id="top_search_form" method="get">
  <a href="/search/advanced" class="advanced-search-icon tooltipped downwards command-bar-search" id="advanced_search" title="Advanced search"><span class="mini-icon mini-icon-advanced-search "></span></a>

  <input type="text" data-hotkey="/ s" name="q" id="js-command-bar-field" placeholder="Search or type a command" tabindex="1" data-username="sgavmp" autocapitalize="off">

    <input type="hidden" name="nwo" value="qos-ch/logback" />

    <div class="select-menu js-menu-container js-select-menu search-context-select-menu">
      <span class="minibutton select-menu-button js-menu-target">
        <span class="js-select-button">This repository</span>
      </span>

      <div class="select-menu-modal-holder js-menu-content js-navigation-container">
        <div class="select-menu-modal">

          <div class="select-menu-item js-navigation-item selected">
            <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
            <input type="radio" name="search_target" value="repository" checked="checked" />
            <div class="select-menu-item-text js-select-button-text">This repository</div>
          </div> <!-- /.select-menu-item -->

          <div class="select-menu-item js-navigation-item">
            <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
            <input type="radio" name="search_target" value="global" />
            <div class="select-menu-item-text js-select-button-text">All repositories</div>
          </div> <!-- /.select-menu-item -->

        </div>
      </div>
    </div>

  <span class="mini-icon help tooltipped downwards" title="Show command bar help">
    <span class="mini-icon mini-icon-help"></span>
  </span>

    <input type="hidden" name="type" value="Code" />

  <input type="hidden" name="ref" value="cmdform">

  <div class="divider-vertical"></div>

    <input type="hidden" class="js-repository-name-with-owner" value="qos-ch/logback"/>
    <input type="hidden" class="js-repository-branch" value="master"/>
    <input type="hidden" class="js-repository-tree-sha" value="8b60a2085d7cee7b9986fcb8c09a6bf907c60a88"/>
</form>
        <ul class="top-nav">
            <li class="explore"><a href="https://github.com/explore">Explore</a></li>
            <li><a href="https://gist.github.com">Gist</a></li>
            <li><a href="/blog">Blog</a></li>
          <li><a href="http://help.github.com">Help</a></li>
        </ul>
      </div>

    

  

    <ul id="user-links">
      <li>
        <a href="https://github.com/sgavmp" class="name">
          <img height="20" src="https://secure.gravatar.com/avatar/9200756e27de936c43f93bb859922c84?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png" width="20" /> sgavmp
        </a>
      </li>

        <li>
          <a href="/new" id="new_repo" class="tooltipped downwards" title="Create a new repo">
            <span class="mini-icon mini-icon-create"></span>
          </a>
        </li>

        <li>
          <a href="/settings/profile" id="account_settings"
            class="tooltipped downwards"
            title="Account settings ">
            <span class="mini-icon mini-icon-account-settings"></span>
          </a>
        </li>
        <li>
          <a class="tooltipped downwards" href="/logout" data-method="post" id="logout" title="Sign out">
            <span class="mini-icon mini-icon-logout"></span>
          </a>
        </li>

    </ul>


<div class="js-new-dropdown-contents hidden">
  <ul class="dropdown-menu">
    <li>
      <a href="/new"><span class="mini-icon mini-icon-create"></span> New repository</a>
    </li>
    <li>
        <a href="https://github.com/qos-ch/logback/issues/new"><span class="mini-icon mini-icon-issue-opened"></span> New issue</a>
    </li>
    <li>
    </li>
    <li>
      <a href="/organizations/new"><span class="mini-icon mini-icon-u-list"></span> New organization</a>
    </li>
  </ul>
</div>


    
  </div>
</div>

      

      

      


            <div class="site hfeed" itemscope itemtype="http://schema.org/WebPage">
      <div class="hentry">
        
        <div class="pagehead repohead instapaper_ignore readability-menu ">
          <div class="container">
            <div class="title-actions-bar">
              

<ul class="pagehead-actions">


    <li class="subscription">
      <form accept-charset="UTF-8" action="/notifications/subscribe" data-autosubmit="true" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="authenticity_token" type="hidden" value="ZXCayntwiqm4nSALzj+V+zwIb86bQKNlxxm1Cr/gkpc=" /></div>  <input id="repository_id" name="repository_id" type="hidden" value="283325" />

    <div class="select-menu js-menu-container js-select-menu">
      <span class="minibutton select-menu-button js-menu-target">
        <span class="js-select-button">
          <span class="mini-icon mini-icon-watching"></span>
          Watch
        </span>
      </span>

      <div class="select-menu-modal-holder js-menu-content">
        <div class="select-menu-modal">
          <div class="select-menu-header">
            <span class="select-menu-title">Notification status</span>
            <span class="mini-icon mini-icon-remove-close js-menu-close"></span>
          </div> <!-- /.select-menu-header -->

          <div class="select-menu-list js-navigation-container">

            <div class="select-menu-item js-navigation-item selected">
              <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
              <div class="select-menu-item-text">
                <input checked="checked" id="do_included" name="do" type="radio" value="included" />
                <h4>Not watching</h4>
                <span class="description">You only receive notifications for discussions in which you participate or are @mentioned.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="mini-icon mini-icon-watching"></span>
                  Watch
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
              <div class="select-menu-item-text">
                <input id="do_subscribed" name="do" type="radio" value="subscribed" />
                <h4>Watching</h4>
                <span class="description">You receive notifications for all discussions in this repository.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="mini-icon mini-icon-unwatch"></span>
                  Unwatch
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
              <div class="select-menu-item-text">
                <input id="do_ignore" name="do" type="radio" value="ignore" />
                <h4>Ignoring</h4>
                <span class="description">You do not receive any notifications for discussions in this repository.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="mini-icon mini-icon-mute"></span>
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

    <li class="js-toggler-container js-social-container starring-container ">
      <a href="/qos-ch/logback/unstar" class="minibutton js-toggler-target star-button starred upwards" title="Unstar this repo" data-remote="true" data-method="post" rel="nofollow">
        <span class="mini-icon mini-icon-remove-star"></span>
        <span class="text">Unstar</span>
      </a>
      <a href="/qos-ch/logback/star" class="minibutton js-toggler-target star-button unstarred upwards" title="Star this repo" data-remote="true" data-method="post" rel="nofollow">
        <span class="mini-icon mini-icon-star"></span>
        <span class="text">Star</span>
      </a>
      <a class="social-count js-social-count" href="/qos-ch/logback/stargazers">245</a>
    </li>

        <li>
          <a href="/qos-ch/logback/fork" class="minibutton js-toggler-target fork-button lighter upwards" title="Fork this repo" rel="nofollow" data-method="post">
            <span class="mini-icon mini-icon-branch-create"></span>
            <span class="text">Fork</span>
          </a>
          <a href="/qos-ch/logback/network" class="social-count">131</a>
        </li>


</ul>

              <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public">
                <span class="repo-label"><span>public</span></span>
                <span class="mega-icon mega-icon-public-repo"></span>
                <span class="author vcard">
                  <a href="/qos-ch" class="url fn" itemprop="url" rel="author">
                  <span itemprop="title">qos-ch</span>
                  </a></span> /
                <strong><a href="/qos-ch/logback" class="js-current-repository">logback</a></strong>
              </h1>
            </div>

            
  <ul class="tabs">
    <li class="pulse-nav"><a href="/qos-ch/logback/pulse" class="js-selected-navigation-item " data-selected-links="pulse /qos-ch/logback/pulse" rel="nofollow"><span class="mini-icon mini-icon-pulse"></span></a></li>
    <li><a href="/qos-ch/logback" class="js-selected-navigation-item selected" data-selected-links="repo_source repo_downloads repo_commits repo_tags repo_branches /qos-ch/logback">Code</a></li>
    <li><a href="/qos-ch/logback/network" class="js-selected-navigation-item " data-selected-links="repo_network /qos-ch/logback/network">Network</a></li>
    <li><a href="/qos-ch/logback/pulls" class="js-selected-navigation-item " data-selected-links="repo_pulls /qos-ch/logback/pulls">Pull Requests <span class='counter'>29</span></a></li>




    <li><a href="/qos-ch/logback/graphs" class="js-selected-navigation-item " data-selected-links="repo_graphs repo_contributors /qos-ch/logback/graphs">Graphs</a></li>


  </ul>
  
<div class="tabnav">

  <span class="tabnav-right">
    <ul class="tabnav-tabs">
          <li><a href="/qos-ch/logback/tags" class="js-selected-navigation-item tabnav-tab" data-selected-links="repo_tags /qos-ch/logback/tags">Tags <span class="counter ">51</span></a></li>
    </ul>
  </span>

  <div class="tabnav-widget scope">


    <div class="select-menu js-menu-container js-select-menu js-branch-menu">
      <a class="minibutton select-menu-button js-menu-target" data-hotkey="w" data-ref="master">
        <span class="mini-icon mini-icon-branch"></span>
        <i>branch:</i>
        <span class="js-select-button">master</span>
      </a>

      <div class="select-menu-modal-holder js-menu-content js-navigation-container">

        <div class="select-menu-modal">
          <div class="select-menu-header">
            <span class="select-menu-title">Switch branches/tags</span>
            <span class="mini-icon mini-icon-remove-close js-menu-close"></span>
          </div> <!-- /.select-menu-header -->

          <div class="select-menu-filters">
            <div class="select-menu-text-filter">
              <input type="text" id="commitish-filter-field" class="js-filterable-field js-navigation-enable" placeholder="Filter branches/tags">
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

          <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket css-truncate" data-tab-filter="branches">

            <div data-filterable-for="commitish-filter-field" data-filterable-type="substring">

                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/LBCLASSIC-304/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="LBCLASSIC-304" rel="nofollow" title="LBCLASSIC-304">LBCLASSIC-304</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/LOGBACK_740/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="LOGBACK_740" rel="nofollow" title="LOGBACK_740">LOGBACK_740</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/LOGBACK_791/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="LOGBACK_791" rel="nofollow" title="LOGBACK_791">LOGBACK_791</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/LOGBACK-829/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="LOGBACK-829" rel="nofollow" title="LOGBACK-829">LOGBACK-829</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/LOGBACK-848/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="LOGBACK-848" rel="nofollow" title="LOGBACK-848">LOGBACK-848</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/LOGBACK-849-alternative/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="LOGBACK-849-alternative" rel="nofollow" title="LOGBACK-849-alternative">LOGBACK-849-alternative</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/allowDefaultSepWithinCurlies/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="allowDefaultSepWithinCurlies" rel="nofollow" title="allowDefaultSepWithinCurlies">allowDefaultSepWithinCurlies</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/bootstrap-twitter/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="bootstrap-twitter" rel="nofollow" title="bootstrap-twitter">bootstrap-twitter</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/callableConnector/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="callableConnector" rel="nofollow" title="callableConnector">callableConnector</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/eventAlteringTurboFilters/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="eventAlteringTurboFilters" rel="nofollow" title="eventAlteringTurboFilters">eventAlteringTurboFilters</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/imports/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="imports" rel="nofollow" title="imports">imports</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/lbcore148/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="lbcore148" rel="nofollow" title="lbcore148">lbcore148</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/lbcore224/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="lbcore224" rel="nofollow" title="lbcore224">lbcore224</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/logback-828/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="logback-828" rel="nofollow" title="logback-828">logback-828</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item selected">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/master/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="master" rel="nofollow" title="master">master</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/receiverComponent/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="receiverComponent" rel="nofollow" title="receiverComponent">receiverComponent</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/receiverTracking/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="receiverTracking" rel="nofollow" title="receiverTracking">receiverTracking</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/serverSide/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="serverSide" rel="nofollow" title="serverSide">serverSide</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/simpleloggerperf/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="simpleloggerperf" rel="nofollow" title="simpleloggerperf">simpleloggerperf</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/substGrammar/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="substGrammar" rel="nofollow" title="substGrammar">substGrammar</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/tracker/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="tracker" rel="nofollow" title="tracker">tracker</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/useContextExecutorService2/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="useContextExecutorService2" rel="nofollow" title="useContextExecutorService2">useContextExecutorService2</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/withoutScala/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="withoutScala" rel="nofollow" title="withoutScala">withoutScala</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/zooml-syslog-rfc5424/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="zooml-syslog-rfc5424" rel="nofollow" title="zooml-syslog-rfc5424">zooml-syslog-rfc5424</a>
                </div> <!-- /.select-menu-item -->
            </div>

              <div class="select-menu-no-results">Nothing to show</div>
          </div> <!-- /.select-menu-list -->


          <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket css-truncate" data-tab-filter="tags">
            <div data-filterable-for="commitish-filter-field" data-filterable-type="substring">

                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.12/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.12" rel="nofollow" title="v_1.0.12">v_1.0.12</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.11/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.11" rel="nofollow" title="v_1.0.11">v_1.0.11</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v1.0.10/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v1.0.10" rel="nofollow" title="v1.0.10">v1.0.10</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.9/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.9" rel="nofollow" title="v_1.0.9">v_1.0.9</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.8/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.8" rel="nofollow" title="v_1.0.8">v_1.0.8</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.7/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.7" rel="nofollow" title="v_1.0.7">v_1.0.7</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.6/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.6" rel="nofollow" title="v_1.0.6">v_1.0.6</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.5/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.5" rel="nofollow" title="v_1.0.5">v_1.0.5</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.4/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.4" rel="nofollow" title="v_1.0.4">v_1.0.4</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.3/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.3" rel="nofollow" title="v_1.0.3">v_1.0.3</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.2/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.2" rel="nofollow" title="v_1.0.2">v_1.0.2</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.1/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.1" rel="nofollow" title="v_1.0.1">v_1.0.1</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_1.0.0/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_1.0.0" rel="nofollow" title="v_1.0.0">v_1.0.0</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.30/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.30" rel="nofollow" title="v_0.9.30">v_0.9.30</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.29/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.29" rel="nofollow" title="v_0.9.29">v_0.9.29</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.28/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.28" rel="nofollow" title="v_0.9.28">v_0.9.28</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.27/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.27" rel="nofollow" title="v_0.9.27">v_0.9.27</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.26/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.26" rel="nofollow" title="v_0.9.26">v_0.9.26</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.25/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.25" rel="nofollow" title="v_0.9.25">v_0.9.25</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.24/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.24" rel="nofollow" title="v_0.9.24">v_0.9.24</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.23/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.23" rel="nofollow" title="v_0.9.23">v_0.9.23</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.22/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.22" rel="nofollow" title="v_0.9.22">v_0.9.22</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v_0.9.21/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v_0.9.21" rel="nofollow" title="v_0.9.21">v_0.9.21</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v0.9.20/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v0.9.20" rel="nofollow" title="v0.9.20">v0.9.20</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/v0.9.18/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="v0.9.18" rel="nofollow" title="v0.9.18">v0.9.18</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.19/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.19" rel="nofollow" title="release_0.9.19">release_0.9.19</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.17/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.17" rel="nofollow" title="release_0.9.17">release_0.9.17</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.16/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.16" rel="nofollow" title="release_0.9.16">release_0.9.16</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.15/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.15" rel="nofollow" title="release_0.9.15">release_0.9.15</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.14/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.14" rel="nofollow" title="release_0.9.14">release_0.9.14</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.13/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.13" rel="nofollow" title="release_0.9.13">release_0.9.13</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.11/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.11" rel="nofollow" title="release_0.9.11">release_0.9.11</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.10/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.10" rel="nofollow" title="release_0.9.10">release_0.9.10</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.9/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.9" rel="nofollow" title="release_0.9.9">release_0.9.9</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.8/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.8" rel="nofollow" title="release_0.9.8">release_0.9.8</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.6/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.6" rel="nofollow" title="release_0.9.6">release_0.9.6</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.9.4/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.9.4" rel="nofollow" title="release-0.9.4">release-0.9.4</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.9.3/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.9.3" rel="nofollow" title="release-0.9.3">release-0.9.3</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.9.2/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.9.2" rel="nofollow" title="release-0.9.2">release-0.9.2</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release_0.9.1/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release_0.9.1" rel="nofollow" title="release_0.9.1">release_0.9.1</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.9/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.9" rel="nofollow" title="release-0.9">release-0.9</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.8.1/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.8.1" rel="nofollow" title="release-0.8.1">release-0.8.1</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.8/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.8" rel="nofollow" title="release-0.8">release-0.8</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.7.1/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.7.1" rel="nofollow" title="release-0.7.1">release-0.7.1</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.7/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.7" rel="nofollow" title="release-0.7">release-0.7</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.6/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.6" rel="nofollow" title="release-0.6">release-0.6</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.5/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.5" rel="nofollow" title="release-0.5">release-0.5</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.4/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.4" rel="nofollow" title="release-0.4">release-0.4</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.3/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.3" rel="nofollow" title="release-0.3">release-0.3</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.2.5/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.2.5" rel="nofollow" title="release-0.2.5">release-0.2.5</a>
                </div> <!-- /.select-menu-item -->
                <div class="select-menu-item js-navigation-item ">
                  <span class="select-menu-item-icon mini-icon mini-icon-confirm"></span>
                  <a href="/qos-ch/logback/blob/release-0.2/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target" data-name="release-0.2" rel="nofollow" title="release-0.2">release-0.2</a>
                </div> <!-- /.select-menu-item -->
            </div>

            <div class="select-menu-no-results">Nothing to show</div>

          </div> <!-- /.select-menu-list -->

        </div> <!-- /.select-menu-modal -->
      </div> <!-- /.select-menu-modal-holder -->
    </div> <!-- /.select-menu -->

  </div> <!-- /.scope -->

  <ul class="tabnav-tabs">
    <li><a href="/qos-ch/logback" class="selected js-selected-navigation-item tabnav-tab" data-selected-links="repo_source /qos-ch/logback">Files</a></li>
    <li><a href="/qos-ch/logback/commits/master" class="js-selected-navigation-item tabnav-tab" data-selected-links="repo_commits /qos-ch/logback/commits/master">Commits</a></li>
    <li><a href="/qos-ch/logback/branches" class="js-selected-navigation-item tabnav-tab" data-selected-links="repo_branches /qos-ch/logback/branches" rel="nofollow">Branches <span class="counter ">24</span></a></li>
  </ul>

</div>

  
  
  


            
          </div>
        </div><!-- /.repohead -->

        <div id="js-repo-pjax-container" class="container context-loader-container" data-pjax-container>
          


<!-- blob contrib key: blob_contributors:v21:045ca2cba92f9b449def2b9e68073dd2 -->
<!-- blob contrib frag key: views10/v8/blob_contributors:v21:045ca2cba92f9b449def2b9e68073dd2 -->


<div id="slider">
    <div class="frame-meta">

      <p title="This is a placeholder element" class="js-history-link-replace hidden"></p>

        <div class="breadcrumb">
          <span class='bold'><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">logback</span></a></span></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">logback-classic</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">src</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src/main" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">main</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src/main/java" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">java</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src/main/java/ch" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">ch</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src/main/java/ch/qos" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">qos</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src/main/java/ch/qos/logback" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">logback</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src/main/java/ch/qos/logback/classic" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">classic</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src/main/java/ch/qos/logback/classic/db" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">db</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/qos-ch/logback/tree/master/logback-classic/src/main/java/ch/qos/logback/classic/db/script" class="js-slide-to" data-branch="master" data-direction="back" itemscope="url"><span itemprop="title">script</span></a></span><span class="separator"> / </span><strong class="final-path">mysql.sql</strong> <span class="js-zeroclipboard zeroclipboard-button" data-clipboard-text="logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" data-copied-hint="copied!" title="copy to clipboard"><span class="mini-icon mini-icon-clipboard"></span></span>
        </div>

      <a href="/qos-ch/logback/find/master" class="js-slide-to" data-hotkey="t" style="display:none">Show File Finder</a>


        
  <div class="commit file-history-tease">
    <img class="main-avatar" height="24" src="https://secure.gravatar.com/avatar/1198ef606e08d27ad98bbe5a2de39229?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png" width="24" />
    <span class="author"><a href="/ceki" rel="author">ceki</a></span>
    <time class="js-relative-date" datetime="2012-05-11T07:34:28-07:00" title="2012-05-11 07:34:28">May 11, 2012</time>
    <div class="commit-title">
        <a href="/qos-ch/logback/commit/f2d15babe3275e7b0d618a020f09a863a0fa6a54" class="message">adding AsyncAppender</a>
    </div>

    <div class="participation">
      <p class="quickstat"><a href="#blob_contributors_box" rel="facebox"><strong>1</strong> contributor</a></p>
      
    </div>
    <div id="blob_contributors_box" style="display:none">
      <h2>Users on GitHub who have contributed to this file</h2>
      <ul class="facebox-user-list">
        <li>
          <img height="24" src="https://secure.gravatar.com/avatar/1198ef606e08d27ad98bbe5a2de39229?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png" width="24" />
          <a href="/ceki">ceki</a>
        </li>
      </ul>
    </div>
  </div>


    </div><!-- ./.frame-meta -->

    <div class="frames">
      <div class="frame" data-permalink-url="/qos-ch/logback/blob/e6319e5fb631d220f23829981e0ad8a13d262ed3/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" data-title="logback/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql at master · qos-ch/logback · GitHub" data-type="blob">

        <div id="files" class="bubble">
          <div class="file">
            <div class="meta">
              <div class="info">
                <span class="icon"><b class="mini-icon mini-icon-text-file"></b></span>
                <span class="mode" title="File Mode">file</span>
                  <span>61 lines (54 sloc)</span>
                <span>1.751 kb</span>
              </div>
              <div class="actions">
                <div class="button-group">
                        <a class="minibutton tooltipped leftwards"
                           title="Clicking this button will automatically fork this project so you can edit the file"
                           href="/qos-ch/logback/edit/master/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql"
                           data-method="post" rel="nofollow">Edit</a>
                  <a href="/qos-ch/logback/raw/master/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="button minibutton " id="raw-url">Raw</a>
                    <a href="/qos-ch/logback/blame/master/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="button minibutton ">Blame</a>
                  <a href="/qos-ch/logback/commits/master/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql" class="button minibutton " rel="nofollow">History</a>
                </div><!-- /.button-group -->
              </div><!-- /.actions -->

            </div>
                <div class="blob-wrapper data type-sql js-blob-data">
      <table class="file-code file-diff">
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

          </td>
          <td class="blob-line-code">
                  <div class="highlight"><pre><div class='line' id='LC1'><span class="o">#</span> <span class="n">Logback</span><span class="p">:</span> <span class="n">the</span> <span class="n">reliable</span><span class="p">,</span> <span class="n">generic</span><span class="p">,</span> <span class="n">fast</span> <span class="k">and</span> <span class="n">flexible</span> <span class="n">logging</span> <span class="n">framework</span><span class="p">.</span></div><div class='line' id='LC2'><span class="o">#</span> <span class="n">Copyright</span> <span class="p">(</span><span class="k">C</span><span class="p">)</span> <span class="mi">1999</span><span class="o">-</span><span class="mi">2010</span><span class="p">,</span> <span class="n">QOS</span><span class="p">.</span><span class="n">ch</span><span class="p">.</span> <span class="k">All</span> <span class="n">rights</span> <span class="n">reserved</span><span class="p">.</span></div><div class='line' id='LC3'><span class="o">#</span></div><div class='line' id='LC4'><span class="o">#</span> <span class="n">See</span> <span class="n">http</span><span class="p">:</span><span class="o">//</span><span class="n">logback</span><span class="p">.</span><span class="n">qos</span><span class="p">.</span><span class="n">ch</span><span class="o">/</span><span class="n">license</span><span class="p">.</span><span class="n">html</span> <span class="k">for</span> <span class="n">the</span> <span class="n">applicable</span> <span class="n">licensing</span> </div><div class='line' id='LC5'><span class="o">#</span> <span class="n">conditions</span><span class="p">.</span></div><div class='line' id='LC6'><br/></div><div class='line' id='LC7'><span class="o">#</span> <span class="n">This</span> <span class="k">SQL</span> <span class="n">script</span> <span class="n">creates</span> <span class="n">the</span> <span class="n">required</span> <span class="n">tables</span> <span class="k">by</span> <span class="n">ch</span><span class="p">.</span><span class="n">qos</span><span class="p">.</span><span class="n">logback</span><span class="p">.</span><span class="n">classic</span><span class="p">.</span><span class="n">db</span><span class="p">.</span><span class="n">DBAppender</span><span class="p">.</span></div><div class='line' id='LC8'><span class="o">#</span></div><div class='line' id='LC9'><span class="o">#</span> <span class="n">It</span> <span class="k">is</span> <span class="n">intended</span> <span class="k">for</span> <span class="n">MySQL</span> <span class="n">databases</span><span class="p">.</span> <span class="n">It</span> <span class="n">has</span> <span class="n">been</span> <span class="n">tested</span> <span class="k">on</span> <span class="n">MySQL</span> <span class="mi">5</span><span class="p">.</span><span class="mi">1</span><span class="p">.</span><span class="mi">37</span> </div><div class='line' id='LC10'><span class="o">#</span> <span class="k">on</span> <span class="n">Linux</span></div><div class='line' id='LC11'><br/></div><div class='line' id='LC12'><br/></div><div class='line' id='LC13'><span class="k">BEGIN</span><span class="p">;</span></div><div class='line' id='LC14'><span class="k">DROP</span> <span class="k">TABLE</span> <span class="n">IF</span> <span class="k">EXISTS</span> <span class="n">logging_event_property</span><span class="p">;</span></div><div class='line' id='LC15'><span class="k">DROP</span> <span class="k">TABLE</span> <span class="n">IF</span> <span class="k">EXISTS</span> <span class="n">logging_event_exception</span><span class="p">;</span></div><div class='line' id='LC16'><span class="k">DROP</span> <span class="k">TABLE</span> <span class="n">IF</span> <span class="k">EXISTS</span> <span class="n">logging_event</span><span class="p">;</span></div><div class='line' id='LC17'><span class="k">COMMIT</span><span class="p">;</span></div><div class='line' id='LC18'><br/></div><div class='line' id='LC19'><br/></div><div class='line' id='LC20'><span class="k">BEGIN</span><span class="p">;</span></div><div class='line' id='LC21'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">logging_event</span> </div><div class='line' id='LC22'>&nbsp;&nbsp;<span class="p">(</span></div><div class='line' id='LC23'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">timestmp</span>         <span class="nb">BIGINT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC24'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">formatted_message</span>  <span class="nb">TEXT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC25'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">logger_name</span>       <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC26'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">level_string</span>      <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC27'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">thread_name</span>       <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">),</span></div><div class='line' id='LC28'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">reference_flag</span>    <span class="nb">SMALLINT</span><span class="p">,</span></div><div class='line' id='LC29'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">arg0</span>              <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">),</span></div><div class='line' id='LC30'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">arg1</span>              <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">),</span></div><div class='line' id='LC31'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">arg2</span>              <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">),</span></div><div class='line' id='LC32'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">arg3</span>              <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">),</span></div><div class='line' id='LC33'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">caller_filename</span>   <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC34'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">caller_class</span>      <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC35'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">caller_method</span>     <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC36'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">caller_line</span>       <span class="nb">CHAR</span><span class="p">(</span><span class="mi">4</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC37'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">event_id</span>          <span class="nb">BIGINT</span> <span class="k">NOT</span> <span class="k">NULL</span> <span class="n">AUTO_INCREMENT</span> <span class="k">PRIMARY</span> <span class="k">KEY</span></div><div class='line' id='LC38'>&nbsp;&nbsp;<span class="p">);</span></div><div class='line' id='LC39'><span class="k">COMMIT</span><span class="p">;</span></div><div class='line' id='LC40'><br/></div><div class='line' id='LC41'><span class="k">BEGIN</span><span class="p">;</span></div><div class='line' id='LC42'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">logging_event_property</span></div><div class='line' id='LC43'>&nbsp;&nbsp;<span class="p">(</span></div><div class='line' id='LC44'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">event_id</span>	      <span class="nb">BIGINT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC45'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">mapped_key</span>        <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC46'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">mapped_value</span>      <span class="nb">TEXT</span><span class="p">,</span></div><div class='line' id='LC47'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">PRIMARY</span> <span class="k">KEY</span><span class="p">(</span><span class="n">event_id</span><span class="p">,</span> <span class="n">mapped_key</span><span class="p">),</span></div><div class='line' id='LC48'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">event_id</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">logging_event</span><span class="p">(</span><span class="n">event_id</span><span class="p">)</span></div><div class='line' id='LC49'>&nbsp;&nbsp;<span class="p">);</span></div><div class='line' id='LC50'><span class="k">COMMIT</span><span class="p">;</span></div><div class='line' id='LC51'><br/></div><div class='line' id='LC52'><span class="k">BEGIN</span><span class="p">;</span></div><div class='line' id='LC53'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">logging_event_exception</span></div><div class='line' id='LC54'>&nbsp;&nbsp;<span class="p">(</span></div><div class='line' id='LC55'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">event_id</span>         <span class="nb">BIGINT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC56'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">i</span>                <span class="nb">SMALLINT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC57'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="n">trace_line</span>       <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">254</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC58'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">PRIMARY</span> <span class="k">KEY</span><span class="p">(</span><span class="n">event_id</span><span class="p">,</span> <span class="n">i</span><span class="p">),</span></div><div class='line' id='LC59'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">event_id</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">logging_event</span><span class="p">(</span><span class="n">event_id</span><span class="p">)</span></div><div class='line' id='LC60'>&nbsp;&nbsp;<span class="p">);</span></div><div class='line' id='LC61'><span class="k">COMMIT</span><span class="p">;</span></div></pre></div>
          </td>
        </tr>
      </table>
  </div>

          </div>
        </div>

        <a href="#jump-to-line" rel="facebox" data-hotkey="l" class="js-jump-to-line" style="display:none">Jump to Line</a>
        <div id="jump-to-line" style="display:none">
          <h2>Jump to Line</h2>
          <form accept-charset="UTF-8" class="js-jump-to-line-form">
            <input class="textfield js-jump-to-line-field" type="text">
            <div class="full-button">
              <button type="submit" class="button">Go</button>
            </div>
          </form>
        </div>

      </div>
    </div>
</div>

<div id="js-frame-loading-template" class="frame frame-loading large-loading-area" style="display:none;">
  <img class="js-frame-loading-spinner" src="https://a248.e.akamai.net/assets.github.com/images/spinners/octocat-spinner-128.gif?1347543525" height="64" width="64">
</div>


        </div>
      </div>
      <div class="context-overlay"></div>
    </div>

      <div id="footer-push"></div><!-- hack for sticky footer -->
    </div><!-- end of wrapper - hack for sticky footer -->

      <!-- footer -->
      <div id="footer">
  <div class="container clearfix">

      <dl class="footer_nav">
        <dt>GitHub</dt>
        <dd><a href="https://github.com/about">About us</a></dd>
        <dd><a href="https://github.com/blog">Blog</a></dd>
        <dd><a href="https://github.com/contact">Contact &amp; support</a></dd>
        <dd><a href="http://enterprise.github.com/">GitHub Enterprise</a></dd>
        <dd><a href="http://status.github.com/">Site status</a></dd>
      </dl>

      <dl class="footer_nav">
        <dt>Applications</dt>
        <dd><a href="http://mac.github.com/">GitHub for Mac</a></dd>
        <dd><a href="http://windows.github.com/">GitHub for Windows</a></dd>
        <dd><a href="http://eclipse.github.com/">GitHub for Eclipse</a></dd>
        <dd><a href="http://mobile.github.com/">GitHub mobile apps</a></dd>
      </dl>

      <dl class="footer_nav">
        <dt>Services</dt>
        <dd><a href="http://get.gaug.es/">Gauges: Web analytics</a></dd>
        <dd><a href="http://speakerdeck.com">Speaker Deck: Presentations</a></dd>
        <dd><a href="https://gist.github.com">Gist: Code snippets</a></dd>
        <dd><a href="http://jobs.github.com/">Job board</a></dd>
      </dl>

      <dl class="footer_nav">
        <dt>Documentation</dt>
        <dd><a href="http://help.github.com/">GitHub Help</a></dd>
        <dd><a href="http://developer.github.com/">Developer API</a></dd>
        <dd><a href="http://github.github.com/github-flavored-markdown/">GitHub Flavored Markdown</a></dd>
        <dd><a href="http://pages.github.com/">GitHub Pages</a></dd>
      </dl>

      <dl class="footer_nav">
        <dt>More</dt>
        <dd><a href="http://training.github.com/">Training</a></dd>
        <dd><a href="https://github.com/edu">Students &amp; teachers</a></dd>
        <dd><a href="http://shop.github.com">The Shop</a></dd>
        <dd><a href="/plans">Plans &amp; pricing</a></dd>
        <dd><a href="http://octodex.github.com/">The Octodex</a></dd>
      </dl>

      <hr class="footer-divider">


    <p class="right">&copy; 2013 <span title="0.07295s from fe17.rs.github.com">GitHub</span>, Inc. All rights reserved.</p>
    <a class="left" href="https://github.com/">
      <span class="mega-icon mega-icon-invertocat"></span>
    </a>
    <ul id="legal">
        <li><a href="https://github.com/site/terms">Terms of Service</a></li>
        <li><a href="https://github.com/site/privacy">Privacy</a></li>
        <li><a href="https://github.com/security">Security</a></li>
    </ul>

  </div><!-- /.container -->

</div><!-- /.#footer -->


    <div class="fullscreen-overlay js-fullscreen-overlay" id="fullscreen_overlay">
  <div class="fullscreen-container js-fullscreen-container">
    <div class="textarea-wrap">
      <textarea name="fullscreen-contents" id="fullscreen-contents" class="js-fullscreen-contents" placeholder="" data-suggester="fullscreen_suggester"></textarea>
          <div class="suggester-container">
              <div class="suggester fullscreen-suggester js-navigation-container" id="fullscreen_suggester"
                 data-url="/qos-ch/logback/suggestions/commit">
              </div>
          </div>
    </div>
  </div>
  <div class="fullscreen-sidebar">
    <a href="#" class="exit-fullscreen js-exit-fullscreen tooltipped leftwards" title="Exit Zen Mode">
      <span class="mega-icon mega-icon-normalscreen"></span>
    </a>
    <a href="#" class="theme-switcher js-theme-switcher tooltipped leftwards"
      title="Switch themes">
      <span class="mini-icon mini-icon-brightness"></span>
    </a>
  </div>
</div>



    <div id="ajax-error-message" class="flash flash-error">
      <span class="mini-icon mini-icon-exclamation"></span>
      Something went wrong with that request. Please try again.
      <a href="#" class="mini-icon mini-icon-remove-close ajax-error-dismiss"></a>
    </div>

    
    
    <span id='server_response_time' data-time='0.07343' data-host='fe17'></span>
    
  </body>
</html>


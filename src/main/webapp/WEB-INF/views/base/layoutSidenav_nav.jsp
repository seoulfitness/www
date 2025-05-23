<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<div id="layoutSidenav_nav">
    <nav class="sidenav shadow-right sidenav-light">
        <div class="sidenav-menu">
            <div class="nav accordion" id="accordionSidenav">
                <%-- Sidenav Menu Heading (Account)--%>
                <%-- * * Note: * * Visible only on and above the sm breakpoint--%>
                <div class="sidenav-menu-heading d-sm-none">Account</div>
                <%-- Sidenav Link (Alerts)--%>
                <%-- * * Note: * * Visible only on and above the sm breakpoint--%>
                <a class="nav-link d-sm-none" href="#!">
                    <div class="nav-link-icon"><i data-feather="bell"></i></div>
                    Alerts
                    <span class="badge bg-warning-soft text-warning ms-auto">4 New!</span>
                </a>
                <%-- Sidenav Link (Messages)--%>
                <%-- * * Note: * * Visible only on and above the sm breakpoint--%>
                <a class="nav-link d-sm-none" href="#!">
                    <div class="nav-link-icon"><i data-feather="mail"></i></div>
                    Messages
                    <span class="badge bg-success-soft text-success ms-auto">2 New!</span>
                </a>
                
                <%-- 메뉴 --%>
                <div class="sidenav-menu-heading">메뉴</div>

                <%-- 지점 --%>
                <a class="nav-link collapsed ${activePage == 'branches' or activePage == 'branchManagers' ? 'active' : ''}" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseBranch" aria-expanded="false" aria-controls="collapseBranch">
                    <div class="nav-link-icon"><i data-feather="grid"></i></div>
                    지점 정보
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse ${activePage == 'branches' or activePage == 'branchManagers' ? 'show' : ''}" id="collapseBranch" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavPages">
                        <a class="nav-link ${activePage == 'branches' ? 'active' : ''}" href="/admin/branches">지점 목록</a>
                        <a class="nav-link ${activePage == 'branchManagers' ? 'active' : ''}" href="/admin/branchManagers">지점 관리자</a>
                    </nav>
                </div>
                <%--// 지점 --%>

                <%-- 대학교 --%>
                <a class="nav-link collapsed ${activePage == 'schools' or activePage == 'departments' ? 'active' : ''}" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseSchool" aria-expanded="false" aria-controls="collapseSchool">
                    <div class="nav-link-icon"><i data-feather="grid"></i></div>
                    대학교 정보
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse ${activePage == 'schools' or activePage == 'departments' ? 'show' : ''}" id="collapseSchool" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavPages">
                        <a class="nav-link ${activePage == 'schools' ? 'active' : ''}" href="/admin/schools">대학교</a>
                        <a class="nav-link ${activePage == 'departments' ? 'active' : ''}" href="/admin/departments">학과</a>              
                    </nav>
                </div>
                <%--// 대학교 --%>

                <%-- 고등학교 --%>
                <a class="nav-link collapsed ${activePage == 'districts' or activePage == 'provinces' or activePage == 'highSchools' ? 'active' : ''}" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseHighSchool" aria-expanded="false" aria-controls="collapseHighSchool">
                    <div class="nav-link-icon"><i data-feather="grid"></i></div>
                    고등학교 정보
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse ${activePage == 'districts' or activePage == 'provinces' or activePage == 'highSchools' ? 'show' : ''}" id="collapseHighSchool" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavPages">
                        <a class="nav-link ${activePage == 'provinces' ? 'active' : ''}" href="/admin/provinces">시/도</a>
                        <a class="nav-link ${activePage == 'districts' ? 'active' : ''}" href="/admin/districts">구/군</a> 
                        <a class="nav-link ${activePage == 'highSchools' ? 'active' : ''}" href="/admin/highSchools">고등학교</a>                 
                    </nav>
                </div>
                <%-- 고등학교 --%>

                <%-- 입시 정보 --%>
                <a class="nav-link collapsed ${activePage == 'csatSubjects' or activePage == 'physicalSubjects' or activePage == 'admissions' or activePage == 'internalSubjects' or activePage == 'internalSubSubjects' ? 'active' : ''}" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseAdmission" aria-expanded="false" aria-controls="collapseAdmission">
                    <div class="nav-link-icon"><i data-feather="grid"></i></div>
                    입시 정보
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse ${activePage == 'csatSubjects' or activePage == 'physicalSubjects' or activePage == 'admissions' or activePage == 'internalSubjects' or activePage == 'internalSubSubjects' ? 'show' : ''}" id="collapseAdmission" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavPages">
                        <a class="nav-link ${activePage == 'admissions' ? 'active' : ''}" href="/admin/admissions">입시 요강</a>
                        <a class="nav-link ${activePage == 'csatSubjects' ? 'active' : ''}" href="/admin/csatSubjects">수능 교과목</a>
                        <a class="nav-link ${activePage == 'physicalSubjects' ? 'active' : ''}" href="/admin/physicalSubjects">실기 교과목</a>
                        <a class="nav-link ${activePage == 'internalSubjects' ? 'active' : ''}" href="/admin/internalSubjects">내신 교과목</a>
                        <a class="nav-link ${activePage == 'internalSubSubjects' ? 'active' : ''}" href="/admin/internalSubSubjects">내신 세부 교과목</a>
                    </nav>
                </div>
                <%--// 입시 정보 --%>

                <%-- 기록 등록 정보 --%>
                <a class="nav-link collapsed ${activePage == 'csatRecordPeriods' or activePage == 'physicalRecordPeriods' or activePage == 'internalRecordPeriods' ? 'active' : ''}" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapsePeriods" aria-expanded="false" aria-controls="collapsePeriods">
                    <div class="nav-link-icon"><i data-feather="grid"></i></div>
                    기록 등록 정보
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse ${activePage == 'csatRecordPeriods' or activePage == 'physicalRecordPeriods' or activePage == 'internalRecordPeriods' ? 'show' : ''}" id="collapsePeriods" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavPages">
                        <a class="nav-link ${activePage == 'csatRecordPeriods' ? 'active' : ''}" href="/admin/csatRecordPeriods">수능 기록 등록 기간</a>
                        <a class="nav-link ${activePage == 'physicalRecordPeriods' ? 'active' : ''}" href="/admin/physicalRecordPeriods">실기 기록 등록 기간</a>
                        <a class="nav-link ${activePage == 'internalRecordPeriods' ? 'active' : ''}" href="/admin/internalRecordPeriods">내신 기록 등록 기간</a>
                    </nav>
                </div>
                <%--// 기록 등록 정보 --%>

                <%-- 사용자 --%>
                <div class="sidenav-menu-heading">사용자</div>

                <%-- 일반 회원 --%>
                <a class="nav-link ${activePage == 'normalUsers' ? 'active' : ''}" href="/admin/normal-users">
                    <div class="nav-link-icon"><i data-feather="user"></i></div>
                    일반 회원
                </a>
                <%--// 일반 회원 --%>

                <%-- 지점 회원 --%>
                <a class="nav-link ${activePage == 'branchUsers' ? 'active' : ''}" href="/admin/branch-users">
                    <div class="nav-link-icon"><i data-feather="user"></i></div>
                    지점 회원
                </a>
                <%--// 지점 회원 --%>


                <%-- Sidenav Heading (Custom)--%>
                <div class="sidenav-menu-heading">사용자</div>
                <%-- Sidenav Accordion (Pages)--%>
                <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                    <div class="nav-link-icon"><i data-feather="grid"></i></div>
                    Pages
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapsePages" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavPagesMenu">
                        <%-- Nested Sidenav Accordion (Pages -> Account)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAccount" aria-expanded="false" aria-controls="pagesCollapseAccount">
                            Account
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="pagesCollapseAccount" data-bs-parent="#accordionSidenavPagesMenu">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="account-profile.html">Profile</a>
                                <a class="nav-link" href="account-billing.html">Billing</a>
                                <a class="nav-link" href="account-security.html">Security</a>
                                <a class="nav-link" href="account-notifications.html">Notifications</a>
                            </nav>
                        </div>
                        <%-- Nested Sidenav Accordion (Pages -> Authentication)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                            Authentication
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="pagesCollapseAuth" data-bs-parent="#accordionSidenavPagesMenu">
                            <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavPagesAuth">
                                <%-- Nested Sidenav Accordion (Pages -> Authentication -> Basic)--%>
                                <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuthBasic" aria-expanded="false" aria-controls="pagesCollapseAuthBasic">
                                    Basic
                                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="pagesCollapseAuthBasic" data-bs-parent="#accordionSidenavPagesAuth">
                                    <nav class="sidenav-menu-nested nav">
                                        <a class="nav-link" href="auth-login-basic.html">Login</a>
                                        <a class="nav-link" href="auth-register-basic.html">Register</a>
                                        <a class="nav-link" href="auth-password-basic.html">Forgot Password</a>
                                    </nav>
                                </div>
                                <%-- Nested Sidenav Accordion (Pages -> Authentication -> Social)--%>
                                <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuthSocial" aria-expanded="false" aria-controls="pagesCollapseAuthSocial">
                                    Social
                                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="pagesCollapseAuthSocial" data-bs-parent="#accordionSidenavPagesAuth">
                                    <nav class="sidenav-menu-nested nav">
                                        <a class="nav-link" href="auth-login-social.html">Login</a>
                                        <a class="nav-link" href="auth-register-social.html">Register</a>
                                        <a class="nav-link" href="auth-password-social.html">Forgot Password</a>
                                    </nav>
                                </div>
                            </nav>
                        </div>
                        <%-- Nested Sidenav Accordion (Pages -> Error)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                            Error
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="pagesCollapseError" data-bs-parent="#accordionSidenavPagesMenu">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="error-400.html">400 Error</a>
                                <a class="nav-link" href="error-401.html">401 Error</a>
                                <a class="nav-link" href="error-403.html">403 Error</a>
                                <a class="nav-link" href="error-404-1.html">404 Error 1</a>
                                <a class="nav-link" href="error-404-2.html">404 Error 2</a>
                                <a class="nav-link" href="error-500.html">500 Error</a>
                                <a class="nav-link" href="error-503.html">503 Error</a>
                                <a class="nav-link" href="error-504.html">504 Error</a>
                            </nav>
                        </div>
                        <a class="nav-link" href="pricing.html">Pricing</a>
                        <a class="nav-link" href="invoice.html">Invoice</a>
                    </nav>
                </div>
                <%-- Sidenav Accordion (Applications)--%>
                <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseApps" aria-expanded="false" aria-controls="collapseApps">
                    <div class="nav-link-icon"><i data-feather="globe"></i></div>
                    Applications
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseApps" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavAppsMenu">
                        <%-- Nested Sidenav Accordion (Apps -> Knowledge Base)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#appsCollapseKnowledgeBase" aria-expanded="false" aria-controls="appsCollapseKnowledgeBase">
                            Knowledge Base
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="appsCollapseKnowledgeBase" data-bs-parent="#accordionSidenavAppsMenu">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="knowledge-base-home-1.html">Home 1</a>
                                <a class="nav-link" href="knowledge-base-home-2.html">Home 2</a>
                                <a class="nav-link" href="knowledge-base-category.html">Category</a>
                                <a class="nav-link" href="knowledge-base-article.html">Article</a>
                            </nav>
                        </div>
                        <%-- Nested Sidenav Accordion (Apps -> User Management)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#appsCollapseUserManagement" aria-expanded="false" aria-controls="appsCollapseUserManagement">
                            User Management
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="appsCollapseUserManagement" data-bs-parent="#accordionSidenavAppsMenu">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="user-management-list.html">Users List</a>
                                <a class="nav-link" href="user-management-edit-user.html">Edit User</a>
                                <a class="nav-link" href="user-management-add-user.html">Add User</a>
                                <a class="nav-link" href="user-management-groups-list.html">Groups List</a>
                                <a class="nav-link" href="user-management-org-details.html">Organization Details</a>
                            </nav>
                        </div>
                        <%-- Nested Sidenav Accordion (Apps -> Posts Management)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#appsCollapsePostsManagement" aria-expanded="false" aria-controls="appsCollapsePostsManagement">
                            Posts Management
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="appsCollapsePostsManagement" data-bs-parent="#accordionSidenavAppsMenu">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="blog-management-posts-list.html">Posts List</a>
                                <a class="nav-link" href="blog-management-create-post.html">Create Post</a>
                                <a class="nav-link" href="blog-management-edit-post.html">Edit Post</a>
                                <a class="nav-link" href="blog-management-posts-admin.html">Posts Admin</a>
                            </nav>
                        </div>
                    </nav>
                </div>
                <%-- Sidenav Accordion (Flows)--%>
                <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseFlows" aria-expanded="false" aria-controls="collapseFlows">
                    <div class="nav-link-icon"><i data-feather="repeat"></i></div>
                    Flows
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseFlows" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav">
                        <a class="nav-link" href="multi-tenant-select.html">Multi-Tenant Registration</a>
                        <a class="nav-link" href="wizard.html">Wizard</a>
                    </nav>
                </div>
                <%-- Sidenav Heading (UI Toolkit)--%>
                <div class="sidenav-menu-heading">UI Toolkit</div>
                <%-- Sidenav Accordion (Layout)--%>
                <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="nav-link-icon"><i data-feather="layout"></i></div>
                    Layout
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavLayout">
                        <%-- Nested Sidenav Accordion (Layout -> Navigation)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseLayoutSidenavVariations" aria-expanded="false" aria-controls="collapseLayoutSidenavVariations">
                            Navigation
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayoutSidenavVariations" data-bs-parent="#accordionSidenavLayout">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="layout-static.html">Static Sidenav</a>
                                <a class="nav-link" href="layout-dark.html">Dark Sidenav</a>
                                <a class="nav-link" href="layout-rtl.html">RTL Layout</a>
                            </nav>
                        </div>
                        <%-- Nested Sidenav Accordion (Layout -> Container Options)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseLayoutContainers" aria-expanded="false" aria-controls="collapseLayoutContainers">
                            Container Options
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayoutContainers" data-bs-parent="#accordionSidenavLayout">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="layout-boxed.html">Boxed Layout</a>
                                <a class="nav-link" href="layout-fluid.html">Fluid Layout</a>
                            </nav>
                        </div>
                        <%-- Nested Sidenav Accordion (Layout -> Page Headers)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseLayoutsPageHeaders" aria-expanded="false" aria-controls="collapseLayoutsPageHeaders">
                            Page Headers
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayoutsPageHeaders" data-bs-parent="#accordionSidenavLayout">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="header-simplified.html">Simplified</a>
                                <a class="nav-link" href="header-compact.html">Compact</a>
                                <a class="nav-link" href="header-overlap.html">Content Overlap</a>
                                <a class="nav-link" href="header-breadcrumbs.html">Breadcrumbs</a>
                                <a class="nav-link" href="header-light.html">Light</a>
                            </nav>
                        </div>
                        <%-- Nested Sidenav Accordion (Layout -> Starter Layouts)--%>
                        <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseLayoutsStarterTemplates" aria-expanded="false" aria-controls="collapseLayoutsStarterTemplates">
                            Starter Layouts
                            <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayoutsStarterTemplates" data-bs-parent="#accordionSidenavLayout">
                            <nav class="sidenav-menu-nested nav">
                                <a class="nav-link" href="starter-default.html">Default</a>
                                <a class="nav-link" href="starter-minimal.html">Minimal</a>
                            </nav>
                        </div>
                    </nav>
                </div>
                <%-- Sidenav Accordion (Components)--%>
                <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseComponents" aria-expanded="false" aria-controls="collapseComponents">
                    <div class="nav-link-icon"><i data-feather="package"></i></div>
                    Components
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseComponents" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav">
                        <a class="nav-link" href="alerts.html">Alerts</a>
                        <a class="nav-link" href="avatars.html">Avatars</a>
                        <a class="nav-link" href="badges.html">Badges</a>
                        <a class="nav-link" href="buttons.html">Buttons</a>
                        <a class="nav-link" href="cards.html">
                            Cards
                            <span class="badge bg-primary-soft text-primary ms-auto">Updated</span>
                        </a>
                        <a class="nav-link" href="dropdowns.html">Dropdowns</a>
                        <a class="nav-link" href="forms.html">
                            Forms
                            <span class="badge bg-primary-soft text-primary ms-auto">Updated</span>
                        </a>
                        <a class="nav-link" href="modals.html">Modals</a>
                        <a class="nav-link" href="navigation.html">Navigation</a>
                        <a class="nav-link" href="progress.html">Progress</a>
                        <a class="nav-link" href="step.html">Step</a>
                        <a class="nav-link" href="timeline.html">Timeline</a>
                        <a class="nav-link" href="toasts.html">Toasts</a>
                        <a class="nav-link" href="tooltips.html">Tooltips</a>
                    </nav>
                </div>
                <%-- Sidenav Accordion (Utilities)--%>
                <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse" data-bs-target="#collapseUtilities" aria-expanded="false" aria-controls="collapseUtilities">
                    <div class="nav-link-icon"><i data-feather="tool"></i></div>
                    Utilities
                    <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseUtilities" data-bs-parent="#accordionSidenav">
                    <nav class="sidenav-menu-nested nav">
                        <a class="nav-link" href="animations.html">Animations</a>
                        <a class="nav-link" href="background.html">Background</a>
                        <a class="nav-link" href="borders.html">Borders</a>
                        <a class="nav-link" href="lift.html">Lift</a>
                        <a class="nav-link" href="shadows.html">Shadows</a>
                        <a class="nav-link" href="typography.html">Typography</a>
                    </nav>
                </div>
                <%-- Sidenav Heading (Addons)--%>
                <div class="sidenav-menu-heading">Plugins</div>
                <%-- Sidenav Link (Charts)--%>
                <a class="nav-link" href="charts.html">
                    <div class="nav-link-icon"><i data-feather="bar-chart"></i></div>
                    Charts
                </a>
                <%-- Sidenav Link (Tables)--%>
                <a class="nav-link" href="tables.html">
                    <div class="nav-link-icon"><i data-feather="filter"></i></div>
                    Tables
                </a>
            </div>
        </div>
        <%-- Sidenav Footer--%>
        <div class="sidenav-footer">
            <div class="sidenav-footer-content">
                <div class="sidenav-footer-subtitle">Logged in as:</div>
                <div class="sidenav-footer-title">Valerie Luna</div>
            </div>
        </div>
    </nav>
</div>
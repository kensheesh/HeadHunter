<#import "../spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resume</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .dropdown {
            display: inline-block;
            position: relative;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: white;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            min-width: 160px;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/"><@spring.message 'navbar.home'/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile"><@spring.message 'navbar.profile'/></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/vacancies"><@spring.message 'navbar.vacancies'/></a>
                </li>

                <#if type = "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/resumes"><@spring.message 'navbar.resumes'/></a>
                    </li>
                </#if>

                <#if type = "APPLICANT">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/employers"><@spring.message 'navbar.employers'/></a>
                    </li>
                </#if>

                <#if type = "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/applicants"><@spring.message 'navbar.applicants'/></a>
                    </li>
                </#if>
                <li class="nav-item">
                    <div class="dropdown">
                        <button class="btn" style="margin-top: 1.4px"><@spring.message 'dropdown' /></button>
                        <div class="dropdown-content">
                            <a href="?lang=ru"><@spring.message 'ru' /></a>
                            <a href="?lang=en"><@spring.message 'en' /></a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow">
                <div class="card-body">
                    <h3 class="card-title text-center text-primary mb-4"><@spring.message 'chat.applicantsInfo'/></h3>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted"><@spring.message 'resumes.authorOfResume'/>:</div>
                        <div class="col-md-8">${resume.user.name} ${resume.user.surname}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Email:</div>
                        <div class="col-md-8">${resume.user.email}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted"><@spring.message 'authentication.phoneNumber'/>:</div>
                        <div class="col-md-8">${resume.user.phoneNumber}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow">
                <div class="card-body">
                    <h3 class="card-title text-center text-primary mb-4"><@spring.message 'applying.resume'/>
                        : ${resume.name}</h3>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted"><@spring.message 'applying.nameOfResume'/>:</div>
                        <div class="col-md-8">${resume.name}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted"><@spring.message 'resumes.category'/></div>
                        <div class="col-md-8">${resume.categoryName}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted"><@spring.message 'applying.salary'/>:</div>
                        <div class="col-md-8">${resume.salary}</div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-4 text-muted"><@spring.message 'resumes.education'/>:</div>
                        <div class="col-md-8">
                            <ul class="list-unstyled">
                                <#list resume.educationInfos as item>
                                    <li>${item.institution} (${item.degree}) - ${item.startDate} - ${item.endDate}</li>
                                </#list>
                            </ul>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-4 text-muted"><@spring.message 'resumes.experienceOfWork'/>:</div>
                        <div class="col-md-8">
                            <ul class="list-unstyled">
                                <#list resume.workExpInfos as item>
                                    <li>
                                        <div><@spring.message 'applying.companyName'/> ${item.companyName}</div>
                                        <div><@spring.message 'resumes.position'/>: ${item.position}</div>
                                        <div><@spring.message 'resumes.responsibilities'/>
                                            : ${item.responsibilities}</div>
                                        <div><@spring.message 'resumes.years'/>: ${item.years}</div>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-4 text-muted"><@spring.message 'resumes.linkToSocialMedia'/>:</div>
                        <div class="col-md-8">
                            <ul class="list-unstyled">
                                <#list resume.contactInfos as contactInfo>
                                    <#if contactInfo.contactType == "Telegram">
                                        <li><@spring.message 'resumes.telegram'/>: ${contactInfo.value}</li>
                                    </#if>
                                    <#if contactInfo.contactType == "Facebook">
                                        <li><@spring.message 'resumes.facebook'/>: ${contactInfo.value}</li>
                                    </#if>
                                    <#if contactInfo.contactType == "Email">
                                        <li>email: ${contactInfo.value}</li>
                                    </#if>
                                    <#if contactInfo.contactType == "LinkedIn">
                                        <li><@spring.message 'resumes.linkedIn'/>: ${contactInfo.value}</li>
                                    </#if>
                                    <#if contactInfo.contactType == "PhoneNumber">
                                        <li><@spring.message 'authentication.phoneNumber'/>: ${contactInfo.value}</li>
                                    </#if>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

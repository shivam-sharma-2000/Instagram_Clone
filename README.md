<div id="top"></div>

[![version][version-shield]][version-shield]
[![Open Issues][github-issues-shield]][github-issues-url]
[![Closed Issues][github-closed-issues-shield]][github-closed-issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- PROJECT LOGO -->
<div align="center">
  
  <img src="images/audio_wares-removebg-preview.png" alt="Logo" width="250" height="250">
  
  <h2 align="center">Lan Flex</h2>

  <p align="center">
    A quick and easy way to view and upload any file from any device to your local server.
    <br />
    <a href="https://github.com/shubham-sharma-0834/lan-flex"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/shubham-sharma-0834/lan-flex">View Demo</a>
    -·-
    <a href="https://github.com/shubham-sharma-0834/lan-flex/issues">Report Bug</a>
    -·-
    <a href="https://github.com/shubham-sharma-0834/lan-flex/issues">Request Feature</a>
  </p>
</div>

# LanFlex

## Table of Contents:

> * [About the Project](#about-the-project)
> * [Built with](#built-with)
> * [Getting Started](#getting-started)
>   * [Prerequisites](#prerequisites)
>   * [Installation](#installation)
> * [Usage](#usage)
>   * [Screenshots](#screenshots)
>   * [Features](#features)
> * [Roadmap](#roadmap)
> * [File Structure](#file-structure)
> * [Code](#code)
>   * [Content](#content)
>   * [Requirements](#requirements)
>   * [Limitations](#limitations)
>   * [Build](#build)
>   * [Deploy (how to install build product)](#deploy-how-to-install-build-product)
> * [Browser Support](#browser-support)
> * [Technical Support or Questions](#technical-support-or-questions)
> * [Resources (Documentation and other links)](#resources-documentation-and-other-links)
> * [Contributing / Reporting issues](#contributing--reporting-issues)
> * [License](#license)
> * [Contact](#contact)
> * [Acknowledgments](#acknowledgments)
> * [Useful Links](#useful-links)

-------------------------------------

## About The Project
LanFlex is a project which can be deployed to a local computer or home PC in a local area network and provides a way to view and upload files (Video, Audio and Images) from all the devices connected in the same network.
It has basically 4 main parts :

> * **Drive Scanner** : A Console application to scan the specified folder and store the file details in the database.
> * **Fron-End** : A Vue JS project which displays the list of files and show the content of the file.
> * **Back-End** : An ASP .Net Core project which provides the WEB APIs to show and manipulate data.
> * **Database** : A SQL Server database to store the files details, User Settings and history.

This project converts your local system or home PC into a private local server. Which can be accessed from any device connected in the same network as the server.

But, of course there are some settings we have to do and some services we need to install. I have explained them in details below.

-------------------------------------
###### TODO: Add steps to install and build the project
-------------------------------------

There are some steps we need to follow before the deploying it :

> * **Install IIS** : IIS is a service which will basically turn your PC into a server. Here we will publish/deploy our backend.You can add IIS from **Turn Windows Features On or Off**
> * Install .net core web hosting
> * Create all file upload path properly
> * Run all the scripts to create the db and db objects
> * Enable inbound ports from the firewall

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Built With

This section should list any major frameworks/libraries used to bootstrap your project. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.

* [![Next][Next.js]][Next-url]
* [![React][React.js]][React-url]
* [![Vue][Vue.js]][Vue-url]
* [![Angular][Angular.io]][Angular-url]
* [![Svelte][Svelte.dev]][Svelte-url]
* [![Laravel][Laravel.com]][Laravel-url]
* [![Bootstrap][Bootstrap.com]][Bootstrap-url]
* [![JQuery][JQuery.com]][JQuery-url]

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* npm
  ```sh
  npm install npm@latest -g
  ```

### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Get a free API Key at [https://example.com](https://example.com)
2. Clone the repo
   ```sh
   git clone https://github.com/your_username_/Project-Name.git
   ```
3. Install NPM packages
   ```sh
   npm install
   ```
4. Enter your API in `config.js`
   ```js
   const API_KEY = 'ENTER YOUR API';
   ```
Sample:

* From the Nuxeo Marketplace: install [the Sample Nuxeo Package](https://connect.nuxeo.com/nuxeo/site/marketplace/package/nuxeo-sample).
* From the command line: `nuxeoctl mp-install nuxeo-sample`

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```
```python
import foobar

# returns 'words'
foobar.pluralize('word')

# returns 'geese'
foobar.pluralize('goose')

# returns 'phenomenon'
foobar.singularize('phenomena')
```

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Usage

The UI contains mainly a dashboard, an upload screen, a history screen and separate screens for each file types.

### Features
> * **Dashboard**: This is the landing page. Here you will see all the type of files present. And total how many files are uploaded so far.
> * **Videos**: This is the place to show all the videos uploaded so far. As soon as the **Play video** button is clicked the video will start playing in the **Video Player**.
> * **Audios**: This is the place to show all the audios uploaded so far. As soon as the **Play Audio** button is clicked the audio will start playing in the **Audio Player**.
> * **Images**: This is the place to show all the images uploaded so far. As soon as the image is clicked the image will open in the **Image Viewer**.
> * **Upload**: Here user will be able to upload any file. The user just needs to select the file type and then the file needs to be uploaded. And once the submit button gets hit the file will be upload and it will be available to use.
> * **Task History**: Here the overall upload history will be visible.
> ***


### Screenshots
[![dashboard][dashboard]][dashboard]
[![videos][videos]][videos]
[![video-player][video-player]][video-player]
[![audios][audios]][audios]
[![audio-player][audio-player]][audio-player]
[![images][images]][images]
[![image-viewer][image-viewer]][image-viewer]
[![upload][upload]][upload]
[![task-history][task-history]][task-history]

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Roadmap

- [x] Scans the whole drive and store that data and file details in the database using the console application or a service
- [x] Connect to the database using the .net backend and get all the files and its details
- [x] Show the files and details in the frontend
- [ ] Use the signalR server hub to notify everyone if there is a change in the server
- [x] Fix the UI issues
    - [x] Video is not aligned going out of the screen
    - [x] Video name should be aligned - wrapped around the text like Avengers 2 (Age of ….)
    - [x] Video name box should be of same size and look consistent
- [ ] Themes and background should be saved in the database for the user
- [ ] User profile page should have a preferences check boxes where user will enter the file preferences like video audio or images or movies tv series or music videos. Preferences button should be in the settings
- [ ] And in the settings images should be selected only if the check box Is selected.
- [x] Show default thumbnail for the video/audio
- [x] Create thumbnails for video in .net - Did not create but showing default image / thumb
- [ ] While uplaoding user has to provide some basic info for the file like file title, description, genre, type (movie/tv series/music video) and file type like (video/audio/image)
- [ ] Other than the above details there will be some more details which will not be shown to the user like date time of the upload, user name who uploaded the file, etc.
- [ ] Create an installer which will install and place all the related file at respective places. And create the service/site and start them.
- [ ] Dashboard items can be restricted from users to user. Like for Admin there should be a field which will show active users / no. Of active users.
- [ ] Use the TMDB API in the project to get the different details of the Movie / TV Series (https://www.themoviedb.org/u/ssharma0834 | https://www.themoviedb.org/documentation/api
  - [ ] To get the movie by id -> https://api.themoviedb.org/3/movie/popular?api_key=cdadec61df96e7b7cdd116432ca9aaa0
  - [ ] To list all the popular movies -> https://api.themoviedb.org/3/discover/movie?api_key=cdadec61df96e7b7cdd116432ca9aaa0
  - [ ] To search for any movie -> https://api.themoviedb.org/3/search/movie?api_key=cdadec61df96e7b7cdd116432ca9aaa0&query=dil


See the [open issues](https://github.com/shubham-sharma-0834/lan-flex/issues) for a full list of proposed features (and known issues).

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## File Structure

Within the download you'll find the following directories and files:

```
LAN-FLEX
├── DB Scrips
├── images
├── LAN Projects
│   ├── LANUI
│   ├── LanFlexWebAPI
├── .gitignore
├── LICENSE
├── README.md
├── Software Require Document.docx
```
<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Code

[![Build Status](https://qa.nuxeo.org/jenkins/buildStatus/icon?job=/nuxeo/addons_nuxeo-sample-project-master)](https://qa.nuxeo.org/jenkins/job/nuxeo/job/addons_nuxeo-sample-project-master/)

### Requirements

See [CORG/Compiling Nuxeo from sources](http://doc.nuxeo.com/x/xION)

Sample: <https://github.com/nuxeo/nuxeo/blob/master/nuxeo-distribution/README.md>

### Limitations

Sample: <https://github.com/nuxeo-archives/nuxeo-features/tree/master/nuxeo-elasticsearch>

### Build

    mvn clean install

Build options:

* ...

### Deploy (how to install build product)

Direct to MP package if any. Otherwise provide steps to deploy on Nuxeo Platform:

 > Copy the built artifacts into `$NUXEO_HOME/templates/custom/bundles/` and activate the `custom` template.

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Browser Support

At present, we officially aim to support the last two versions of the following browsers:

<img src="https://s3.amazonaws.com/creativetim_bucket/github/browser/chrome.png" width="64" height="64">
<img src="https://s3.amazonaws.com/creativetim_bucket/github/browser/firefox.png" width="64" height="64">
<img src="https://s3.amazonaws.com/creativetim_bucket/github/browser/edge.png" width="64" height="64">
<img src="https://s3.amazonaws.com/creativetim_bucket/github/browser/safari.png" width="64" height="64">
<img src="https://s3.amazonaws.com/creativetim_bucket/github/browser/opera.png" width="64" height="64">


## Technical Support or Questions

If you have questions or need help integrating the product please [contact us](https://www.creative-tim.com/contact-us) instead of opening an issue.

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Resources (Documentation and other links)

- [Live Preview](https://demos.creative-tim.com/vuetify-material-dashboard?ref=vtymdp-readme)
- Download Page: [Download](https://www.creative-tim.com/product/vuetify-material-dashboard?ref=vtymdp-readme)
- Documentation is [Here](https://vuetifyjs.com/en/components/api-explorer)
- License Agreement: [License](https://www.creative-tim.com/license)
- Support: [Contact](https://www.creative-tim.com/contact-us)
- Issues: [Github Issues Page](https://github.com/creativetimofficial/ct-vuetify-material-dashboard/issues)
- Vue Material Dashboard PRO - [Demo](https://www.creative-tim.com/product/vue-material-dashboard-pro?ref=vtymdp-readme)
- For Front End Development - [Material Kit Pro ](https://www.creative-tim.com/product/material-kit-pro?ref=vtymdp-readme)

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Contributing / Reporting issues

Link to JIRA component (or project if there is no component for that project). Samples:

* [Link to component](https://jira.nuxeo.com/issues/?jql=project%20%3D%20NXP%20AND%20component%20%3D%20Elasticsearch%20AND%20Status%20!%3D%20%22Resolved%22%20ORDER%20BY%20updated%20DESC%2C%20priority%20DESC%2C%20created%20ASC)
* [Link to project](https://jira.nuxeo.com/secure/CreateIssue!default.jspa?project=NXP)

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

We use GitHub Issues as the official bug tracker for the **Vuetify Material Dashboard**. Here are some advices for our users that want to report an issue:

1. Make sure that you are using the latest version of the **Vuetify Material Dashboard**. Check the **CHANGELOG** from your dashboard on our [website](https://www.creative-tim.com/).
2. Providing us reproducible steps for the issue will shorten the time it takes for it to be fixed.
3. Some issues may be browser specific, so specifying in what browser you encountered the issue might help.

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Contact

Your Name - [@your_twitter](https://twitter.com/your_username) - email@example.com

Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------

## Useful Links

- [More products](https://www.creative-tim.com/bootstrap-themes) from Creative Tim

- [Vue products](https://www.creative-tim.com/bootstrap-themes/vuejs-themes) from Creative Tim

- [Tutorials](https://www.youtube.com/channel/UCVyTG4sCw-rOvB9oHkzZD1w)

- [Freebies](https://www.creative-tim.com/bootstrap-themes/free) from Creative Tim

- [Affiliate Program](https://www.creative-tim.com/affiliates/new) (earn money)


[![Twitter][twitter-shield]][twitter-url]
[![Facebook][facebook-shield]][facebook-url]
[![Instagram][instagram-shield]][instagram-url]

<p align="right">[<a href="#top">Back to Top &#8593;</a>]</p>

-------------------------------------


<!-- MARKDOWN LINKS & IMAGES -->

[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge

[license-url]: https://github.com/shubham-sharma-0834/lan-flex/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/othneildrew

[version-shield]: https://img.shields.io/badge/VERSION-1.0-brightgreen?style=for-the-badge

[github-issues-shield]: https://img.shields.io/github/issues/shubhamsharma11/lanflex?label=Open%20issues&style=for-the-badge

[github-issues-url]: https://github.com/shubhamsharma11/lan-flex/issues

[github-closed-issues-shield]: https://img.shields.io/github/issues-closed-raw/shubhamsharma11/lanflex?color=blue&label=closed%20issues&style=for-the-badge

[github-closed-issues-url]: https://github.com/shubhamsharma11/lan-flex/issues?q=is%3Aissue+is%3Aclosed

[dashboard]: images/screenshots/dashboard.jpeg

[videos]: images/screenshots/videos.jpeg

[video-player]: images/screenshots/video%20player.jpeg

[audios]: images/screenshots/audios.jpeg

[audio-player]: images/screenshots/audio%20player.jpeg

[images]: images/screenshots/images.jpg

[image-viewer]: images/screenshots/image%20viewer.jpeg

[upload]: images/screenshots/upload.jpg

[task-history]: images/screenshots/task%20history.jpg

[product-screenshot2]: images/branch.png

[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white

[Next-url]: https://nextjs.org/

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB

[React-url]: https://reactjs.org/

[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D

[Vue-url]: https://vuejs.org/

[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white

[Angular-url]: https://angular.io/

[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00

[Svelte-url]: https://svelte.dev/

[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white

[Laravel-url]: https://laravel.com

[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white

[Bootstrap-url]: https://getbootstrap.com

[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white

[JQuery-url]: https://jquery.com 

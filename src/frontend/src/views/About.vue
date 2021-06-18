<template>
  <div class="about bg-image">
    <div class="bg-dark aboutThis mx-auto">
      <h1>About This</h1>
      <p class="aboutThisText">
        This is a small project that we've been working on just for fun and to learn fullstack development. Lorem, ipsum dolor sit amet consectetur adipisicing elit. Deserunt saepe nisi doloremque repudiandae deleniti quia aliquid perferendis culpa autem eligendi suscipit, earum praesentium placeat atque similique! Enim architecto ipsum adipisci!
      </p>
      <a class="githubButton" href="https://github.com/Andr3wD/OurStory">
        <b-icon class="githubIcon" icon="github" aria-hidden="true"></b-icon>
      </a>
    </div>
    <h2>The Team</h2>
    <!-- https://www.w3schools.com/howto/howto_css_about_page.asp -->
    <b-container>
      <b-row cols="3">
        <b-col>
          <!-- https://bootstrap-vue.org/docs/components/card -->
          <!-- TODO: duplicate for all devs eventually -->
          <b-card no-body class="developerCard">
            <b-card-title title-tag="h2">Andrew Day</b-card-title>
            <!-- Placeholder image. TODO eventually change. -->
            <b-card-img top class="developerImage" src="https://mdbcdn.b-cdn.net/img/Photos/Others/images/76.jpg"/>
            <b-card-body>
              <a class="developerGithubButton" href="https://github.com/Andr3wD">
                <b-icon class="githubIcon" icon="github" aria-hidden="true"></b-icon>
              </a>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<style>

.developerGithubButton{
  width: 50px;
  height: 50px;
}

.developerImage{
  width: 100%;
}

.developerCard{
  padding: 15px;
  width: 20vw;
}

.about{
  align-content: center;
  height: 100vh;
  /* background-image: url('https://mdbcdn.b-cdn.net/img/Photos/Others/images/76.jpg') */
}

.aboutThis{
  color: aliceblue;
  width: 60vw;
  padding: 20px;
  text-align: center;
  margin: 10px;
  overflow: auto;
}

.aboutThisText{
  text-align: left;
  font-size: 1.5em;
}

.githubIcon{
  width: inherit;
  height: inherit;
  align-content: center;
}

.githubButton{
  width: 70px;
  height: 70px;
  color: aliceblue;
}

</style>

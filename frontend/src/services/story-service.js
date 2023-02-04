import axios from 'axios'

class StoryService {
  getGlobalStory () {
    return axios.get('/story/front-page')
  }
}

export default new StoryService()

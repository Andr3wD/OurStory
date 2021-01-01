import axios from 'axios'

class StoryService {
  getGlobalStory () {
    return axios.get('/story/global')
  }
}

export default new StoryService()

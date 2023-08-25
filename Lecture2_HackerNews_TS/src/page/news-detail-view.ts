import View from "../core/view";
import { NewsDetailApi } from "./../core/api";
import { NewsDetail, NewsComment, NewsStore } from "../types";
import { CONTENT_URL } from "../config";
import Store from "../store";

export default class NewsDetailView extends View {
  store: NewsStore;
  constructor(containerId: string, store: NewsStore) {
    let template = `
<div class="bg-gray-600 min-h-screen pb-8" style="width:600px; margin:auto;">
  <div class="bg-white text-xl">
    <div class="mx-auto px-4">
      <div class="flex justify-between items-center py-6">
        <div class="flex justify-start">
          <h1 class="font-extrabold">Hacker News</h1>
        </div>
        <div class="items-center jusify-end">
          <a href="#/page/{{__currentPage__}}" class="text-gray-500">
            <i class="fa fa-times"></i>
          </a>
        </div>
      </div>
    </div>
  </div>
  <div class="h-full border rounded-xl bg-white m-6 p-4">
    <h2>{{__title__}}</h2>
    <div class="text-gray-400 h-20">{{__content__}}</div>
    {{__comments__}}
  </div>
</div>;`;

    super(containerId, template);
    this.store = store;
  }
  render() {
    const id = location.hash.substr(7);
    const api = new NewsDetailApi(CONTENT_URL.replace("@id", id));
    const newsDetail: NewsDetail = api.getData();

    for (let i = 0; i < this.store.getAllFeeds().length; i++) {
      if (this.store.getFeed(i).id === Number(id)) {
        this.store.getFeed(i).read = true;
        break;
      }
    }
    this.setTemplateData("comments", this.makeComment(newsDetail.comments));
    this.setTemplateData("currentPage", String(this.store.currentPage));
    this.setTemplateData("title", newsDetail.title);
    this.setTemplateData("content", newsDetail.content);

    this.updateview();

    // template.replace(
    //   "{{__comments__}}",
    //   this.makeComment(newsDetail.comments)
    // )
  }

  makeComment(comments: NewsComment[]): string {
    for (let i = 0; i < comments.length; i++) {
      const comment: NewsComment = comments[i];

      this.addHtml(`<div style="padding-left : ${
        comment.level * 40
      }px" class="mt-4">
  <div class="text-gray-400" style="width:600px;>
    <i class="fa fa-sort-up mr-2"></i>
    <strong>${comment.user}</strong>${comment.time_ago}
  </div>
  <p class="text-gray-700">${comment.content}</p>
</div>`);

      if (comment.comments.length > 0) {
        this.addHtml(this.makeComment(comment.comments));
      }
    }
    return this.getHtml();
  }
}

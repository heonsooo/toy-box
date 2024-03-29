<script>
import {
  mapState,
  mapGetters,
  mapMutations,
  createNamespacedHelpers,
} from "vuex";
import store from "/src/store";

const americanoHelper = createNamespacedHelpers("module_americano");

export default {
  data() {
    return {
      pageTitle: "Americano Page A",
      instanceDataOfPageA: 72,
    };
  },
  computed: {
    // index.js의 state
    ...mapState(["COMMON_COFFEE"]),

    // module_americano.js의 state 불러오는 3가지 방법
    ...americanoHelper.mapState(["icedAmericano", "americanoBarista"]),
    // ...mapState("module_americano", ["icedAmericano"]),
    // ...createNamespacedHelpers("module_americano").mapState(["icedAmericano"]),

    // ...americanoHelper.mapState({
    //   icedAmericanoNaming: (state) => state.icedAmericano,
    // }),
    // ...createNamespacedHelpers("module_americano").mapState({
    //   icedAmericanoNaming: (state) => state.icedAmericano,
    // }),
    // ...mapState("module_americano", {
    //   icedAmericanoNaming: (state) => state.icedAmericano,
    // }),

    // index.js의 getters
    ...mapGetters(["COMMON_totalCoffeeCount", "COMMON_totalCoffeePrice"]),
  },
  methods: {
    ...mapMutations([
      "COMMON_incrementCoffeeCount",
      "COMMON_decrementCoffeeCount",
    ]),

    // module_americano.js의 mutations 불러오는 3가지 방법
    ...americanoHelper.mapMutations([
      "incrementIcedAmericanoPrice",
      "decrementIcedAmericanoPrice",
    ]),
    ...americanoHelper.mapActions(["getAmericanoBaristar"]),
    // ...createNamespacedHelpers("module_americano").mapMutations([
    //   "incrementIcedAmericanoPrice",
    //   "decrementIcedAmericanoPrice",
    // ]),
    // ...mapMutations("module_americano", [
    //   "incrementIcedAmericanoPrice",
    //   "decrementIcedAmericanoPrice",
    // ]),

    onClickAddNum() {
      this.instanceDataOfPageA++;
    },
    onClickMinusNum() {
      this.instanceDataOfPageA--;
    },
    onClickAddIcedAmericano() {},
    onClickGetBaristar() {
      let baristarId = Math.floor(Math.random() * 10 + 1);
      this.getAmericanoBaristar(baristarId);
    },
  },
};
</script>

<template>
  <section class="pageA-border">
    <h2>{{ pageTitle }}</h2>
    <picture>
      <img
        class="img-box"
        src="../../assets/img/americano.jpg"
        alt="americano"
      />
    </picture>
    <article label="instance-data">
      <h4>page A 인스턴스 변수</h4>
      <ul>
        <li>
          별점
          <span class="desc">(default : 72) :</span>
          <strong>{{ instanceDataOfPageA }}</strong>
        </li>
      </ul>
      <button class="btn" type="button" @click="onClickAddNum()">증가</button>
      <button class="btn" type="button" @click="onClickMinusNum()">감소</button>
    </article>
    <article label="vuex-data">
      <h4>page A Vuex state (americano.js)</h4>
      <ul>
        <li>
          {{ icedAmericano.name }} 가격 :
          <strong>{{ mixinComma(icedAmericano.price) }} </strong> 원
        </li>
      </ul>
      <button class="btn" type="button" @click="incrementIcedAmericanoPrice()">
        증액
      </button>
      <button class="btn" type="button" @click="decrementIcedAmericanoPrice()">
        감액
      </button>
    </article>
    <article label="vuex-data-advance">
      <h4>page A, Page B Vuex 공통 state (index.js)</h4>
      <ul v-for="(coffee, idx) in COMMON_COFFEE" :key="`coffee${idx}`">
        <li>{{ coffee.name }} : {{ coffee.count }}개</li>
      </ul>
      <ul>
        <li>
          <p>
            총 <strong>{{ COMMON_totalCoffeeCount }} </strong>개
          </p>
        </li>
      </ul>
      <button class="btn" type="button" @click="COMMON_incrementCoffeeCount(0)">
        아아추가
      </button>
      <button class="btn" type="button" @click="COMMON_decrementCoffeeCount(0)">
        아아감소
      </button>
    </article>
    <article class="vuex-action">
      <h4>
        page A 아메리카노 바리스타 조회 (Vuex actions 통신) (americano.js)
      </h4>
      <template v-if="americanoBarista?.avatar">
        <ul>
          <figure>
            <img :src="americanoBarista?.avatar" alt="avatar" />
          </figure>
          <li class="avatar-desc">
            name : {{ americanoBarista?.first_name }}
            {{ americanoBarista?.last_name }}
          </li>
          <li>email : {{ americanoBarista?.email }}</li>
        </ul>
      </template>
      <button class="btn" type="button" @click="onClickGetBaristar()">
        바리스타 조회
      </button>
    </article>
  </section>
</template>

<style scoped>
.pageA-border {
  border: 0.5px solid #c59e9e;
  padding: 10px;
  margin: 10px;
}
.btn {
  margin-left: 10px;
}
.desc {
  color: grey;
  font-size: 9pt;
}
.vuex-action {
  margin-top: 30px;
}
.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-right: 10px;
}
.avatar-desc {
  font-size: 14pt;
  font-weight: bold;
}
.img-box {
  /* background-image: url("../../assets/img/americano.jpg"); */
  background-size: cover;
  width: 400px;
  height: 400px;
}
</style>

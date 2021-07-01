<template>
  <v-card>
    <v-card-title>
      Groups
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addGather">Summon Gather</v-btn>
      <v-btn @click="allGathers">All Gathers</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="gathers"
      :search="search"
      @click:row="editGather"
    ></v-data-table>
    <GatherDialog
      :opened="dialogVisible"
      :gather="selectedGather"
      :userId="this.$store.state.auth.user.id"
      @refresh="refreshList"
    ></GatherDialog>
  </v-card>
</template>

<script>
import router from "../router";
import api from "../api";
import GatherDialog from "../components/GatherDialog";

export default {
  name: "JoinedGatherList",
  components: { GatherDialog },
  data() {
    return {
      gathers: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Subject", value: "subject" },
        { text: "Date Created", value: "dateCreated" },
      ],
      dialogVisible: false,
      selectedGather: {},
    };
  },
  methods: {
    editGather(gather) {
      this.selectedGather = gather;
      router.push({
        name: "GatherPage",
        params: { gatherId: this.selectedGather.id },
      });
    },
    addGather() {
      this.dialogVisible = true;
    },
    allGathers() {
      router.push("/allGathers");
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedGather = {};
      this.gathers = await api.gathers.joinedGatherList(
        this.$store.state.auth.user.id
      );
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>

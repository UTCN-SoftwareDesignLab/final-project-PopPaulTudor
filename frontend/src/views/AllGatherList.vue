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
      <v-btn @click="myGathers">My Gathers</v-btn>
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
import api from "../api";
import GatherDialog from "../components/GatherDialog";
import router from "../router";

export default {
  name: "GatherList",
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
      userId: this.$store.state.auth.user.id,
    };
  },
  methods: {
    editGather(gather) {
      this.selectedGather = gather;
      this.dialogVisible = true;
    },
    addGather() {
      this.dialogVisible = true;
    },
    myGathers() {
      router.push("/myGathers");
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedGather = {};
      this.gathers = await api.gathers.allGathersPresent(
        this.userId
      );
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>

import axios, { AxiosInstance } from "axios";

const SERVER = import.meta.env.VITE_SERVER;

export const defaultAxios: AxiosInstance = axios.create({
  baseURL: SERVER,
});

export const authAxios: AxiosInstance = axios.create({
  baseURL: SERVER,
  headers: {
    Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
  },
});

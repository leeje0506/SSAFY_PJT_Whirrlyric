import axios, {
  // AxiosError,
  AxiosInstance,
  // AxiosRequestConfig,
  // AxiosResponse,
} from "axios";
// import { loginAPI } from "./loginAPI";

// interface CustomAxiosRequestConfig extends AxiosRequestConfig {
//   _retryCount?: number;
// }

const SERVER = import.meta.env.VITE_SERVER;

export const defaultAxios: AxiosInstance = axios.create({
  baseURL: SERVER,
});

export const authAxios: AxiosInstance = axios.create({
  baseURL: SERVER,
});

authAxios.interceptors.request.use((config) => {
  const accessToken = localStorage.getItem("accessToken");
  
  if (accessToken) {
    config.headers['Authorization'] = `Bearer ${accessToken}`;
  }
  
  return config;
}, (error) => {
  return Promise.reject(error);
});

// authAxios.interceptors.response.use(
//   (response: AxiosResponse) => {
//     return response;
//   },
//   async (error: AxiosError) => {
//     const originalRequest = error.config as CustomAxiosRequestConfig;

//     if (originalRequest._retryCount === undefined) {
//       originalRequest._retryCount = 0;
//     }

//     if (error.response?.status === 404 && originalRequest._retryCount < 3) {
//       originalRequest._retryCount += 1;
//       try {
//         const { data } = await loginAPI.reissueToken();
//         localStorage.setItem("accessToken", data.accessToken);
//         authAxios.defaults.headers.common[
//           "Authorization"
//         ] = `Bearer ${data.accessToken}`;
//         return authAxios(originalRequest);
//       } catch (refreshError) {
//         return Promise.reject(refreshError);
//       }
//     }
//     return Promise.reject(error);
//   }
// );

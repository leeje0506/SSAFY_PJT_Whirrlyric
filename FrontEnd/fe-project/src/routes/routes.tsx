import { createBrowserRouter } from "react-router-dom";
import RootLayout from "../pages/RootLayout";
import Login from "../pages/Login";
import MainPage from "../pages/MainPage";
import MyPage from "../pages/MyPage";
import WriteSong from "../pages/WriteSong";
import PlaySong from "../pages/PlaySong";
import ChangeName from "../pages/ChangeName";

const routes = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    children: [
      {
        path: "/",
        element: <Login />,
      },
      {
        path: "main",
        element: <MainPage />,
      },
      {
        path: "write-song",
        element: <WriteSong />,
      },
      {
        path: "play-song/:songId",
        element: <PlaySong />,
      },
      {
        path: "mypage",
        element: <MyPage />,
      },
      {
        path: "change-name",
        element: <ChangeName />,
      },
    ],
  },
]);

export default routes;

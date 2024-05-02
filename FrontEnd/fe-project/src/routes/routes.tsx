import { createBrowserRouter } from "react-router-dom";
import RootLayout from "../pages/RootLayout";
import Login from "../pages/Login";
import Main from "../pages/Main";
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
        element: <Main />,
      },
      {
        path: "write-song",
        element: <WriteSong />,
      },
      {
        path: "play-song",
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

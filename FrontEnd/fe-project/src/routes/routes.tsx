import { createBrowserRouter } from "react-router-dom";
import RootLayout from "../pages/RootLayout";
import Login from "../pages/Login";
import Main from "../pages/Main";
import MyPage from "../pages/MyPage";
import MakeSong from "../pages/MakeSong";
import PlaySong from "../pages/PlaySong";

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
        path: "make-song",
        element: <MakeSong />,
      },
      {
        path: "play-song",
        element: <PlaySong />,
      },
      {
        path: "mypage",
        element: <MyPage />,
      },
    ],
  },
]);

export default routes;

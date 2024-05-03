import { mypageAPI } from "../api/mypageAPI";
import PlayList from "../components/mypage/PlayList";
import Profile from "../components/mypage/Profile";
import { useEffect, useState } from "react";

export default function MyPage() {
  const [user, setUser] = useState(null);

  const getMypageInfo = async () => {
    try {
      const response = await mypageAPI.getMypageInfo(1);
      setUser(response.data);

      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getMypageInfo();
  }, []);

  return (
    <div>
      {user && <Profile user={user} />}
      <PlayList />
    </div>
  );
}

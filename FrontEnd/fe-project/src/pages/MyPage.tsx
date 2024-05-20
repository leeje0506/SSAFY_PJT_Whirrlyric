import { useParams } from "react-router-dom";
import { mypageAPI } from "../api/mypageAPI";
import PlayList from "../components/mypage/PlayList";
import Profile from "../components/mypage/Profile";
import { useEffect, useState } from "react";

export default function MyPage() {
  const { memberId } = useParams();
  const mypageMemberId = Number(memberId);
  
  const memberIdStr = localStorage.getItem("memberId");
  const currentMemberId = Number(memberIdStr);

  const requestMemberId = mypageMemberId ? mypageMemberId : currentMemberId;

  const [user, setUser] = useState<MemberProfile | null>(null);

  const getMypageInfo = async (memberId:number) => {
    try {
      const response = await mypageAPI.getMypageInfo(memberId);
      setUser(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getMypageInfo(requestMemberId);
  }, []);

  return (
    <div>
      {user && <Profile user={user} isMypage={requestMemberId === currentMemberId} />}
      {user && <PlayList songList={user.songList} />}
    </div>
  );
}

import { useNavigate } from "react-router-dom";
import altUserImg from "../../assets/altImages/altUserImg.png";
import { mypageAPI } from "../../api/mypageAPI";
import pencilIcon from "../../assets/icons/mypage/pencilIcon";
import KakaoLogout from "../common/KakaoLogout.tsx";
import ImageUrl1 from "../../assets/profileImage/profile1.png";
import ImageUrl2 from "../../assets/profileImage/profile2.png";
import ImageUrl3 from "../../assets/profileImage/profile3.png";
import ImageUrl4 from "../../assets/profileImage/profile4.png";
import ImageUrl5 from "../../assets/profileImage/profile5.png";
import ImageUrl6 from "../../assets/profileImage/profile6.png";
import ImageUrl7 from "../../assets/profileImage/profile7.png";
import ImageUrl8 from "../../assets/profileImage/profile8.png";
import ImageUrl9 from "../../assets/profileImage/profile9.png";
import ImageUrl10 from "../../assets/profileImage/profile10.png";

interface ProfileProps {
  user: MemberProfile;
  isMypage: boolean;
}

export default function Profile({ user, isMypage }: ProfileProps) {
  const navigate = useNavigate();
  const getImage = [
    { id: 1, image: ImageUrl1 },
    { id: 2, image: ImageUrl2 },
    { id: 3, image: ImageUrl3 },
    { id: 4, image: ImageUrl4 },
    { id: 5, image: ImageUrl5 },
    { id: 6, image: ImageUrl6 },
    { id: 7, image: ImageUrl7 },
    { id: 8, image: ImageUrl8 },
    { id: 9, image: ImageUrl9 },
    { id: 10, image: ImageUrl10 },
  ];

  const changeMainSong = async (selectedSongId: number) => {
    try {
      const response = await mypageAPI.changeMyMainSong(
        user.nickname,
        selectedSongId
      );

      console.log(selectedSongId);
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  const onTitleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedSongId = Number(event.target.value);
    changeMainSong(selectedSongId);
  };

  return (
    <>
      <div className="flex items-center w-[356px] h-[117px] mx-auto">
        <img
          src={getImage[user.imageUrl - 1].image || altUserImg}
          className="w-28 h-28 rounded-full bg-gray-200 border-gray-400 border-2"
        />
        <div className="ml-6 flex-col">
          <div className="flex">
            <h1>{user.nickname}</h1>
            {isMypage && (
              <button className="ml-2" onClick={() => navigate("/change-name")}>
                {pencilIcon}
              </button>
            )}
          </div>

          <form className="max-w-sm mx-auto">
            <label
              htmlFor="songList"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              My Title Song
            </label>
            <select
              id="songList"
              defaultValue={user.mainSong?.song.songId || ""}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={onTitleChange}
              disabled={!isMypage}
            >
              <option value="" disabled>
                Choose a song
              </option>
              {user.songList &&
                user.songList.map((songWithCreator) => (
                  <option
                    key={songWithCreator.song.songId}
                    value={songWithCreator.song.songId}
                  >
                    {songWithCreator.song.title}
                  </option>
                ))}
            </select>
          </form>
        </div>
      </div>
      {isMypage && (
        <div className="flex justify-end mr-8">
          <KakaoLogout />
        </div>
      )}
      <hr className="mt-6 border-black" />
    </>
  );
}

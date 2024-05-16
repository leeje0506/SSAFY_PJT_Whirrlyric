import { useNavigate } from "react-router-dom";
import altUserImg from "../../assets/altImages/altUserImg.png";
import { mypageAPI } from "../../api/mypageAPI";
import pencilIcon from "../../assets/icons/mypage/pencilIcon";
import KakaoLogout from "../common/KakaoLogout.tsx";
import getUserImage from "../../utils/getUserImage.tsx";

interface ProfileProps {
  user: MemberProfile;
  isMypage: boolean;
}

export default function Profile({ user, isMypage }: ProfileProps) {
  const navigate = useNavigate();

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
          src={getUserImage[user.imageUrl - 1].image || altUserImg}
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

import { useNavigate } from "react-router-dom";
import { makeMusicAPI } from "../api/makeMusicAPI.tsx";
import LyricsItem from "../components/makesongpage/LyricsItem.tsx";
import { useEffect, useState } from "react";

export default function WriteSong() {
  const navigate = useNavigate();

  const [lyricsList, setLyricsList] = useState<lyrics[] | null>(null);

  const [genreList, setGenreList] = useState<genre[] | null>(null);

  const [guide, setGuide] = useState<string>();

  const [modalVisible, setModalVisible] = useState(false); // 상태 추가

  // const [formData, setFormData] = useState<createMusicForm>({
  //     title: '',
  //     intro: '',
  //     verse1: '',
  //     verse2: '',
  //     chorus: '',
  //     bridge: '',
  //     outro: '',
  //     genre: '',
  // });

  // 음악 목록
  const getLyricsList = async () => {
    try {
      const response = await makeMusicAPI.getLyricsList();

      // const response = [{"id" : 1, "lyricsname" : "title"},{"id" : 2, "lyricsname" : "intro"},{"id" : 3, "lyricsname" : "verse1"},{"id" : 4, "lyricsname" : "verse2"},{"id" : 5, "lyricsname" : "chorus"},{"id" : 6, "lyricsname" : "bridge"},{"id" : 7, "lyricsname" : "outro"}];
      // 여기서 response.data를 직접 사용하기 전에 데이터 구조를 로그로 확인
      console.log(response);
      setLyricsList(response.data); // JSON 배열을 상태로 저장
    } catch (error) {
      console.error(error);
    }
  };

  // 장르 목록 - option으로 넣을 것!
  const getGenreList = async () => {
    try {
      await makeMusicAPI
        .getGenreList()
        .then((response) => {
          // console.log("GENRE");
          // console.log("data : " + response.data);
          // console.log("********");
          setGenreList(response.data);
        })
        .catch((error) => console.log(error));
    } catch (error) {
      console.log(error);
    }
  };

  // const handleSubmit= async (data : createMusicForm) =>{
  //         await makeMusicAPI.createMusic(data).then((response)=>
  //             // navigate 갈 것.
  //             console.log(response.data))
  //             .catch((error)=>console.log(error));
  // }

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const data: createMusicForm = {
      title: formData.get("title") as string,
      intro: formData.get("intro") as string,
      verse1: formData.get("verse1") as string,
      verse2: formData.get("verse2") as string,
      chorus: formData.get("chorus") as string,
      bridge: formData.get("bridge") as string,
      outro: formData.get("outro") as string,
      genre: formData.get("genre") as string,
    };

    console.log("Data to be sent:", data);

    try {
      const response = await makeMusicAPI.createMusic(data);
      console.log("Response:", response.data);
      // 노래 상세 페이지로 navigate 갈 것.
      navigate(`play-song/${response.data.songId}`);
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  // 가이드 아이콘
  const guideIcon = (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth={1.5}
      stroke="currentColor"
      className="w-6 h-6"
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        d="M9.879 7.519c1.171-1.025 3.071-1.025 4.242 0 1.172 1.025 1.172 2.687 0 3.712-.203.179-.43.326-.67.442-.745.361-1.45.999-1.45 1.827v.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Zm-9 5.25h.008v.008H12v-.008Z"
      />
    </svg>
  );

  const clickGuide = async () => {
    const response = await makeMusicAPI.getGuide();
    console.log(response.data.description);
    setGuide(response.data.description);
    setModalVisible(true);
  };

  const toggleModal = () => {
    setModalVisible(false); // 모달 가시성 토글
  };

  useEffect(() => {
    getLyricsList();
  }, []);

  useEffect(() => {
    getGenreList();
  }, []);

  return (
    <div className="mx-8 flex-col">
      <div className="flex justify-end">
        {/*<div>*/}
        {/*<h1 className="text-3xl text-center mb-5">Make Song</h1>*/}
        {/*</div>*/}
        <div className="">
          <button
            data-modal-target="static-modal"
            data-modal-toggle="static-modal"
            className="flex-1 justify-end text-white bg-blue-gray-400 hover:bg-amber-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
            type="button"
            onClick={clickGuide}
          >
            {guideIcon}
          </button>
          <div
            id="static-modal"
            data-modal-backdrop="static"
            aria-hidden="true"
            className={`${
              modalVisible ? "" : "hidden"
            } overflow-y-auto overflow-x-hidden fixed top-50 right-0 left-50 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full`}
          >
            <div className="relative p-4 w-full max-w-2xl max-h-full">
              {/*// <!-- Modal content -->*/}
              <div className="relative bg-white rounded-lg shadow dark:bg-gray-700">
                {/*// <!-- Modal header -->*/}
                <div className="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                  <h3 className="text-xl font-semibold text-gray-900 dark:text-white font-['Pretendard'] font-extrabold">
                    만들어, 노래! 노래 만들기 가이드라인
                  </h3>
                  <button
                    type="button"
                    className="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                    data-modal-hide="static-modal"
                    onClick={toggleModal}
                  >
                    <svg
                      className="w-3 h-3"
                      aria-hidden="true"
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 14 14"
                    >
                      <path
                        stroke="currentColor"
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth="2"
                        d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
                      />
                    </svg>
                    <span className="sr-only">Close modal</span>
                  </button>
                </div>
                {/*// <!-- Modal body -->*/}
                <div className="p-4 md:p-5 space-y-4">
                  <p className="text-base leading-relaxed text-gray-500 dark:text-gray-400 font-['Pretendard'] font-medium">
                    {guide}
                  </p>
                </div>
                {/*// <!-- Modal footer -->*/}
                <div className="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600">
                  <button
                    data-modal-hide="static-modal"
                    type="button"
                    onClick={toggleModal}
                    className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 font-['Pretendard'] font-extrabold"
                  >
                    닫기
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div>
        <form onSubmit={handleSubmit} className="w-full">
          {lyricsList ? (
            lyricsList.map((lyric) => (
              <LyricsItem key={lyric.id} lyric={lyric} />
            ))
          ) : (
            <div className="text-lg">No created song</div>
          )}

          <h1 className="text-3xl font-['Pretendard'] font-extrabold">Genre</h1>
          <div className="flex-col my-6">
            <div>
              <select
                className="w-full border-solid bg-gray-200 text-2xl font-['Pretendard'] font-extrabold"
                name="genre"
              >
                {genreList && genreList.length > 0 ? (
                  genreList.map((genre) => (
                    <option
                      key={genre.id}
                      value={genre.genrename}
                      className="w-full"
                    >
                      {genre.genrename}
                    </option>
                  ))
                ) : (
                  <option className="w-full"> No genres available</option>
                )}
              </select>
            </div>
            <div>
              <input
                type="submit"
                value="Done"
                className="mx-auto my-6 text-center text-3xl font-['Pretendard'] font-extrabold bg-black text-white rounded-2xl w-full"
              />
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}
